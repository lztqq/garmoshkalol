
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;

public class StopCmd extends MusicCommand {

    public StopCmd(Bot bot)
    {
        super(bot);
        this.name = "stop";
        this.help = "stops the current song and clears the queue";
        this.bePlaying = false;
        this.category = bot.DJ;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        if(handler!=null)
            handler.stopAndClear();
        event.getGuild().getAudioManager().closeAudioConnection();
        event.reply(event.getClient().getSuccess()+" The player has stopped and the queue has been cleared.");
    }
    
}
