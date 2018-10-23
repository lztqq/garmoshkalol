
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import net.dv8tion.jda.core.entities.User;

public class PauseCmd extends MusicCommand {

    public PauseCmd(Bot bot)
    {
        super(bot);
        this.name = "pause";
        this.help = "pauses the current song";
        this.bePlaying = true;
        this.category = bot.DJ;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        if(handler.getPlayer().isPaused())
        {
            event.replyWarning("The player is already paused! Use `"+event.getClient().getPrefix()+"play` to unpause!");
            return;
        }
        handler.getPlayer().setPaused(true);
        event.replySuccess("Paused **"+handler.getPlayer().getPlayingTrack().getInfo().title+"**. Type `"+event.getClient().getPrefix()+"play` to unpause!");
    }
    
}
