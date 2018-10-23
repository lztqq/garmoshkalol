
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import com.jagrosh.jmusicbot.utils.FormatUtil;

public class VolumeCmd extends MusicCommand {

    public VolumeCmd(Bot bot)
    {
        super(bot);
        this.name = "volume";
        this.aliases = new String[]{"vol"};
        this.help = "sets or shows volume";
        this.arguments = "[0-150]";
        this.category = bot.DJ;
    }

    @Override
    public void doCommand(CommandEvent event) {
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        int volume = handler==null && bot.getSettings(event.getGuild())==null ? 100 : (handler==null ? bot.getSettings(event.getGuild()).getVolume() : handler.getPlayer().getVolume());
        if(event.getArgs().isEmpty())
        {
            event.reply(FormatUtil.volumeIcon(volume)+" Current volume is `"+volume+"`");
        }
        else
        {
            int nvolume;
            try{
                nvolume = Integer.parseInt(event.getArgs());
            }catch(NumberFormatException e){
                nvolume = -1;
            }
            if(nvolume<0 || nvolume>150)
                event.reply(event.getClient().getError()+" Volume must be a valid integer between 0 and 150!");
            else
            {
                bot.setUpHandler(event).getPlayer().setVolume(nvolume);
                bot.setVolume(event.getGuild(), nvolume);
                event.reply(FormatUtil.volumeIcon(nvolume)+" Volume changed from `"+volume+"` to `"+nvolume+"`");
            }
        }
    }
    
}
