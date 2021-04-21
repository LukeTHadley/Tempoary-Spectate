package com.github.lukethadley.temporaryspectate;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    private TemporarySpectate plugin;

    public CommandHandler(TemporarySpectate plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage((plugin.getInformationColor() + plugin.getPrefix() + " Only Players can do this."));
            return true;
        }
        Player commandSender = plugin.getServer().getPlayer(sender.getName());
        plugin.toggleSpec(commandSender);

        return false;
    }
}
