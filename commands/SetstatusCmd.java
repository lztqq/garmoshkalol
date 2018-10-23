
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import net.dv8tion.jda.core.OnlineStatus;

public class SetstatusCmd extends Command {

    public SetstatusCmd(Bot bot)
    {
        this.name = "setstatus";
        this.help = "sets the status the bot displays";
        this.arguments = "<status>";
        this.ownerCommand = true;
        this.category = bot.OWNER;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        try {
            OnlineStatus status = OnlineStatus.fromKey(event.getArgs());
            if(status==OnlineStatus.UNKNOWN)
            {
                event.replyError("Please include one of the following statuses: `ONLINE`, `IDLE`, `DND`, `INVISIBLE`");
            }
            else
            {
                event.getJDA().getPresence().setStatus(status);
                event.replySuccess("Set the status to `"+status.getKey().toUpperCase()+"`");
            }
        } catch(Exception e) {
            event.reply(event.getClient().getError()+" The status could not be set!");
        }
    }
    
}
