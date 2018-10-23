
package com.jagrosh.jmusicbot.commands;

import java.util.List;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jmusicbot.Bot;
import com.jagrosh.jmusicbot.utils.FinderUtil;
import com.jagrosh.jmusicbot.utils.FormatUtil;
import net.dv8tion.jda.core.entities.Role;

public class SetdjCmd extends Command {

    private final Bot bot;
    public SetdjCmd(Bot bot)
    {
        this.bot = bot;
        this.name = "setdj";
        this.help = "sets the DJ role for certain music commands";
        this.arguments = "<rolename|NONE>";
        this.guildOnly = true;
        this.category = bot.ADMIN;
    }
    
    @Override
    protected void execute(CommandEvent event) {
        if(event.getArgs().isEmpty())
        {
            event.reply(event.getClient().getError()+" Please include a role name or NONE");
        }
        else if(event.getArgs().equalsIgnoreCase("none"))
        {
            bot.clearRole(event.getGuild());
            event.reply(event.getClient().getSuccess()+" DJ role cleared; Only Admins can use the DJ commands.");
        }
        else
        {
            List<Role> list = FinderUtil.findRole(event.getArgs(), event.getGuild());
            if(list.isEmpty())
                event.reply(event.getClient().getWarning()+" No Roles found matching \""+event.getArgs()+"\"");
            else if (list.size()>1)
                event.reply(event.getClient().getWarning()+FormatUtil.listOfRoles(list, event.getArgs()));
            else
            {
                bot.setRole(list.get(0));
                event.reply(event.getClient().getSuccess()+" DJ commands can now be used by users with the **"+list.get(0).getName()+"** role.");
            }
        }
    }
    
}
