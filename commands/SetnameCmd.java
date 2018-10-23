
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class SetnameCmd extends Command {

    public SetnameCmd(Bot bot)
    {
        this.name = "setname";
        this.help = "sets the name of the bot";
        this.arguments = "<name>";
        this.ownerCommand = true;
        this.category = bot.OWNER;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        try {
            String oldname = event.getSelfUser().getName();
            event.getSelfUser().getManager().setName(event.getArgs()).complete(false);
            event.reply(event.getClient().getSuccess()+" Name changed from `"+oldname+"` to `"+event.getArgs()+"`");
        } catch(RateLimitedException e) {
            event.reply(event.getClient().getError()+" Name can only be changed twice per hour!");
        } catch(Exception e) {
            event.reply(event.getClient().getError()+" That name is not valid!");
        }
    }
    
}
