
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;

public class ShutdownCmd extends Command {

    private final Bot bot;
    public ShutdownCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "shutdown";
        this.help = "safely shuts down";
        this.ownerCommand = true;
        this.category = bot.OWNER;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        event.reply(event.getClient().getWarning()+" Shutting down...");
        bot.shutdown();
    }
    
}
