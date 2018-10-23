
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.core.Permission;

public class NowplayingCmd extends MusicCommand {

    public NowplayingCmd(Bot bot)
    {
        super(bot);
        this.name = "nowplaying";
        this.help = "shows the song that is currently playing";
        this.aliases = new String[]{"np","current"};
        this.botPermissions = new Permission[]{Permission.MESSAGE_EMBED_LINKS};
    }

    @Override
    public void doCommand(CommandEvent event) {
        event.reply(FormatUtil.nowPlayingMessage(event.getGuild(), event.getClient().getSuccess()), m->bot.setLastNP(m));
    }
    
}
