package com.github.lukethadley.temporaryspectate;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class TemporarySpectate extends JavaPlugin {

    private static final String prefix = "[Temporary Spectate] ";
    private static final String informationColor = ChatColor.YELLOW + "";
    private HashMap<Player, Location> toggled;

    public static String getPrefix() {
        return prefix;
    }

    public static String getInformationColor() {
        return informationColor;
    }

    public HashMap<Player, Location> getToggled() {
        return toggled;
    }

    public void setToggled(HashMap<Player, Location> toggled) {

    }

    public void messageConsole(String str)
    {
        System.out.println("[" + this.getName()  + "]" + " " + str);
    }

    public void messagePlayer(Player player, String str)
    {
        player.sendMessage(informationColor + prefix + str);
    }

    @Override
    public void onEnable() {
        messageConsole("Starting Plugin...");
        toggled = new HashMap<>();
        getServer().getPluginManager().registerEvents(new listeners(), this);
        getCommand("tempoaryspectate").setExecutor(new CommandHandler(this));
    }

    @Override
    public void onDisable() {
        messageConsole("Stopping My Plugin...");
    }

    public void toggleSpec(Player player){
        if (toggled.containsKey(player)){
            messagePlayer(player, "Teleporting you to your last survival co-ords and set gamemode to 'Survival'");
            player.teleport(toggled.get(player));
            player.setGameMode(GameMode.SURVIVAL);
            toggled.remove(player);
        }
        else {
            toggled.put(player, player.getLocation());
            messagePlayer(player, "Set gamemode to 'Spectator'");
            player.setGameMode(GameMode.SPECTATOR);
        }
    }

    public class listeners implements Listener
    {
        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event)
        {
            Player player = event.getPlayer();
            messagePlayer(player, "Do '/spec' to toggle yourself between survival and spectator mode.");
        }

        @EventHandler
        public void onQuit(PlayerQuitEvent event) {
            if (toggled.containsKey(event.getPlayer())){
                toggleSpec(event.getPlayer());
            }

        }
    }
    
}
