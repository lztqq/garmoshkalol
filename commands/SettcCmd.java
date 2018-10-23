
package com.jagrosh.jmusicbot.commands;

import java.util.List;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.utils.FinderUtil;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.core.entities.TextChannel;

public class SettcCmd extends Command {

    private final Bot bot;
    public SettcCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "settc";
        this.help = "sets the text channel for music commands";
        this.arguments = "<channel|NONE>";
        this.guildOnly = true;
        this.category = bot.ADMIN;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs().isEmpty())
        {
            event.reply(event.getClient().getError()+" Please include a text channel or NONE");
        }
        else if(event.getArgs().equalsIgnoreCase("none"))
        {
            bot.clearTextChannel(event.getGuild());
            event.reply(event.getClient().getSuccess()+" Music commands can now be used in any channel");
        }
        else
        {
            List<TextChannel> list = FinderUtil.findTextChannel(event.getArgs(), event.getGuild());
            if(list.isEmpty())
                event.reply(event.getClient().getWarning()+" No Text Channels found matching \""+event.getArgs()+"\"");
            else if (list.size()>1)
                event.reply(event.getClient().getWarning()+FormatUtil.listOfTChannels(list, event.getArgs()));
            else
            {
                bot.setTextChannel(list.get(0));
                event.reply(event.getClient().getSuccess()+" Music commands can now only be used in <#"+list.get(0).getId()+">");
            }
        }
    }
    
}
