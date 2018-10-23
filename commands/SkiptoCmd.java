
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.audio.AudioHandler;
import net.dv8tion.jda.core.entities.User;

public class SkiptoCmd extends MusicCommand {

    public SkiptoCmd(Bot bot)
    {
        super(bot);
        this.name = "skipto";
        this.help = "skips to the specified song";
        this.arguments = "<position>";
        this.aliases = new String[]{"jumpto"};
        this.bePlaying = true;
        this.category = bot.DJ;
    }

    @Override
    public void doCommand(CommandEvent event) {
        int index = 0;
        try
        {
            index = Integer.parseInt(event.getArgs());
        }
        catch(NumberFormatException e)
        {
            event.reply(event.getClient().getError()+" `"+event.getArgs()+"` is not a valid integer!");
            return;
        }
        AudioHandler handler = (AudioHandler)event.getGuild().getAudioManager().getSendingHandler();
        if(index<1 || index>handler.getQueue().size())
        {
            event.reply(event.getClient().getError()+" Position must be a valid integer between 1 and "+handler.getQueue().size()+"!");
            return;
        }
        handler.getQueue().skip(index-1);
        event.reply(event.getClient().getSuccess()+" Skipped to **"+handler.getQueue().get(0).getTrack().getInfo().title+"**");
        handler.getPlayer().stopTrack();
    }
    
}
