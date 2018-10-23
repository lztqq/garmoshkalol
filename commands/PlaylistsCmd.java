
package com.jagrosh.jmusicbot.commands;

import java.util.List;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.playlist.Playlist;

public class PlaylistsCmd extends MusicCommand {

    public PlaylistsCmd(Bot bot)
    {
        super(bot);
        this.name = "playlists";
        this.help = "shows the available playlists";
        this.aliases = new String[]{"pls"};
        this.guildOnly = true;
        this.beListening = false;
        this.beListening = false;
    }
    
    @Override
    public void doCommand(CommandEvent event) {
        if(!Playlist.folderExists())
            Playlist.createFolder();
        if(!Playlist.folderExists())
        {
            event.reply(event.getClient().getWarning()+" Playlists folder does not exist and could not be created!");
            return;
        }
        List<String> list = Playlist.getPlaylists();
        if(list==null)
            event.reply(event.getClient().getError()+" Failed to load available playlists!");
        else if(list.isEmpty())
            event.reply(event.getClient().getWarning()+" There are no playlists in the Playlists folder!");
        else
        {
            StringBuilder builder = new StringBuilder(event.getClient().getSuccess()+" Available playlists:\n");
            list.forEach(str -> builder.append("`").append(str).append("` "));
            builder.append("\nType `").append(event.getClient().getTextualPrefix()).append("play playlist <name>` to play a playlist");
            event.reply(builder.toString());
        }
    }
    
}
