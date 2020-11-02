package tempspec;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {

    private static String prefix = "[Temporary Spectate] ";
    private static String informationColor = ChatColor.YELLOW + "";
    private HashMap<Player, Location> toggled;

    private void messageConsole(String str)
    {
        System.out.println("[" + this.getName()  + "]" + " " + str);
    }

    private void messagePlayer(Player player, String str)
    {
        player.sendMessage(informationColor + prefix + str);
    }

    @Override
    public void onEnable()
    {
        messageConsole("Starting Plugin...");
        toggled = new HashMap<>();
        getServer().getPluginManager().registerEvents(new listeners(), this);
    }

    @Override
    public void onDisable()
    {
        messageConsole("Stopping My Plugin...");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)){
            sender.sendMessage((informationColor + prefix + " Only Players can do this."));
            return true;
        }
        Player commandSender = this.getServer().getPlayer(sender.getName());
        if (command.getName().equalsIgnoreCase("spec") || command.getName().equalsIgnoreCase("tempspec")) {
            if (args.length > 0){
                if(args[0].equalsIgnoreCase("help")){   //Help Command
                    messagePlayer(commandSender, "This command will set your gamemode to spectator until you type '/spec' again.\n" +
                            "It will teleport you back to your last co-ords that you had in survival gamemode and set you back into survival.");
                    return false;
                }
            }
            toggleSpec(commandSender);
        }
        return false;
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
