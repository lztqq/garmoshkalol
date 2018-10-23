
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;

public class RepeatCmd extends Command {

    private final Bot bot;
    public RepeatCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "repeat";
        this.help = "re-adds music to the queue when finished";
        this.arguments = "[on|off]";
        this.guildOnly = true;
        this.category = bot.DJ;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        boolean value;
        if(event.getArgs().isEmpty())
        {
            value = !bot.getSettings(event.getGuild()).getRepeatMode();
        }
        else if(event.getArgs().equalsIgnoreCase("true") || event.getArgs().equalsIgnoreCase("on"))
        {
            value = true;
        }
        else if(event.getArgs().equalsIgnoreCase("false") || event.getArgs().equalsIgnoreCase("off"))
        {
            value = false;
        }
        else
        {
            event.replyError("Valid options are `on` or `off` (or leave empty to toggle)");
            return;
        }
        bot.setRepeatMode(event.getGuild(), value);
        event.replySuccess("Repeat mode is now `"+(value ? "ON" : "OFF")+"`");
    }
    
}
