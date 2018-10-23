
package com.jagrosh.jmusicbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.Settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class SettingsCmd extends Command {

    private final Bot bot;
    public SettingsCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "settings";
        this.help = "shows the bots settings";
        this.aliases = new String[]{"status"};
        this.guildOnly = true;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        Settings s = bot.getSettings(event.getGuild());
        MessageBuilder builder = new MessageBuilder()
                .append("\uD83C\uDFA7 **")
                .append(event.getSelfUser().getName())
                .append("** settings:");
        TextChannel tchan = event.getGuild().getTextChannelById(s.getTextId());
        VoiceChannel vchan = event.getGuild().getVoiceChannelById(s.getVoiceId());
        Role role = event.getGuild().getRoleById(s.getRoleId());
        EmbedBuilder ebuilder = new EmbedBuilder()
                .setColor(event.getSelfMember().getColor())
                .setDescription("Text Channel: "+(tchan==null ? "Any" : "**#"+tchan.getName()+"**")
                        + "\nVoice Channel: "+(vchan==null ? "Any" : "**"+vchan.getName()+"**")
                        + "\nDJ Role: "+(role==null ? "None" : "**"+role.getName()+"**")
                        + "\nRepeat Mode: **"+(s.getRepeatMode() ? "On" : "Off")+"**"
                        + "\nDefault Playlist: "+(s.getDefaultPlaylist()==null ? "None" : "**"+s.getDefaultPlaylist()+"**")
                        )
                .setFooter(event.getJDA().getGuilds().size()+" servers | "
                        +event.getJDA().getGuilds().stream().filter(g -> g.getSelfMember().getVoiceState().inVoiceChannel()).count()
                        +" audio connections", null);
        event.getChannel().sendMessage(builder.setEmbed(ebuilder.build()).build()).queue();
    }
    
}
