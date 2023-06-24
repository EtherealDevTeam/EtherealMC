package me.etherealcraft.etherealcore.commands;

import me.etherealcraft.etherealcore.EtherealCore;
import me.etherealcraft.etherealcore.files.PlayerDataConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class HomeCommand implements CommandExecutor {
    private final EtherealCore plugin;

    public HomeCommand(EtherealCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //make sure that they are a player
        if (sender instanceof Player){
            File file = new File(Bukkit.getServer().getPluginManager().getPlugin("EtherealCore").getDataFolder(), "/PlayerData/" + PlayerDataConfig.playerUUID + ".yml");
            Player player = (Player) sender;
            if(player.hasPermission("etherealcore.commands.home")) {
                //see if the plugin has a spawn point set in the config
                String homeName = args[0];
                Location location = PlayerDataConfig.get().getLocation(String.valueOf("Homes." + player.getLocation().getWorld().getName()) + "." + homeName);
                if (location != null) {

                    if(args.length == 0) {
                        //send a message to the player
                        player.sendMessage("You must specify a home.");
                        player.sendMessage("Usage: /home [homename]");
                    }else{
                        player.teleport(PlayerDataConfig.get().getLocation(String.valueOf("Homes." + player.getLocation().getWorld().getName()) + "." + homeName));
                        player.sendMessage("You have been teleported to " + String.valueOf(homeName));
                    }
                }else {
                    player.sendMessage("The home has not been set.");
                }
            }else{
                player.sendMessage("You do not have the required permission (etherealcore.commands.home)");
            }
        }else{
            Bukkit.getLogger().info("This command can not be run in console.");
        }

        return true;
    }
}
