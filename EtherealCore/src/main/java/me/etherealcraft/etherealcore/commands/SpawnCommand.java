package me.etherealcraft.etherealcore.commands;

import me.etherealcraft.etherealcore.EtherealCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final EtherealCore plugin;

    public SpawnCommand(EtherealCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //make sure that they are a player
        if (sender instanceof Player){

            Player player = (Player) sender;
            if(player.hasPermission("etherealcore.commands.spawn")) {
                //see if the plugin has a spawn point set in the config
                Location location = plugin.getConfig().getLocation(String.valueOf(player.getLocation().getWorld().getName()));
                if (location != null) {

                    if(args.length == 0) {
                        //teleport the player to the spawn point
                        player.teleport(location);

                        //send a message to the player
                        player.sendMessage("You have been teleported to the spawn point.");
                    }else{
                        String world = args[0];
                        player.teleport(plugin.getConfig().getLocation(String.valueOf(world)));
                        player.sendMessage("You have been teleported to " + String.valueOf(world) + "'s spawn point.");
                    }
                }else {
                    player.sendMessage("The spawn point has not been set.");
                }
            }else{
                player.sendMessage("You do not have the required permission (etherealcore.commands.spawn)");
            }
        }else{
            Bukkit.getLogger().info("This command can not be run in console.");
        }

        return true;
    }
}
