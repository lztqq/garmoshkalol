
package com.jagrosh.jmusicbot.commands;

import java.util.List;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.utils.FinderUtil;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class SetvcCmd extends Command {

    private final Bot bot;
    public SetvcCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "setvc";
        this.help = "sets the voice channel for playing music";
        this.arguments = "<channel|NONE>";
        this.guildOnly = true;
        this.category = bot.ADMIN;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs().isEmpty())
        {
            event.reply(event.getClient().getError()+" Please include a voice channel or NONE");
        }
        else if(event.getArgs().equalsIgnoreCase("none"))
        {
            bot.clearVoiceChannel(event.getGuild());
            event.reply(event.getClient().getSuccess()+" Music can now be played in any channel");
        }
        else
        {
            List<VoiceChannel> list = FinderUtil.findVoiceChannel(event.getArgs(), event.getGuild());
            if(list.isEmpty())
                event.reply(event.getClient().getWarning()+" No Voice Channels found matching \""+event.getArgs()+"\"");
            else if (list.size()>1)
                event.reply(event.getClient().getWarning()+FormatUtil.listOfVChannels(list, event.getArgs()));
            else
            {
                bot.setVoiceChannel(list.get(0));
                event.reply(event.getClient().getSuccess()+" Music can now only be played in **"+list.get(0).getName()+"**");
            }
        }
    }
    
}
