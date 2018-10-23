
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;

public class ShuffleCmd extends MusicCommand {

    public ShuffleCmd(Bot bot)
    {
        super(bot);
        this.name = "shuffle";
        this.help = "shuffles songs you have added";
        this.beListening = true;
        this.bePlaying = true;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        int s = handler.getQueue().shuffle(event.getAuthor().getIdLong());
        switch (s) {
            case 0:
                event.reply(event.getClient().getError()+" You don't have any music in the queue to shuffle!");
                break;
            case 1:
                event.reply(event.getClient().getWarning()+" You only have one song in the queue!");
                break;
            default:
                event.reply(event.getClient().getSuccess()+" You successfully shuffled your "+s+" entries.");
                break;
        }
    }
    
}
