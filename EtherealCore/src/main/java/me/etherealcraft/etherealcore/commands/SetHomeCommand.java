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

public class SetHomeCommand implements CommandExecutor {

    private final EtherealCore etherealCore;

    public SetHomeCommand(EtherealCore etherealCore){
        this.etherealCore = etherealCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin("EtherealCore").getDataFolder(), "/PlayerData/" + PlayerDataConfig.playerUUID + ".yml");
        if (file.exists()) {
            //Make sure that they are a player
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("etherealcore.commands.sethome")) {
                    //get the players location
                    Location location = player.getLocation();
                    if (args.length != 0) {
                        //set the home location in the playerdata.yml
                        String homeName = args[0];
                        PlayerDataConfig.get().set(String.valueOf("Homes." + location.getWorld().getName()) + "." + homeName, location);

                        //save the UUID.yml
                        PlayerDataConfig.save();

                        //send a message to the player
                        player.sendMessage("Home location set!");
                    } else {
                        player.sendMessage("You must set a home name");
                        player.sendMessage("Usage: /sethome [homename]");
                    }
                } else {
                    player.sendMessage("You do not have the required permission (etherealcore.commands.sethome)");
                }
            } else {
                Bukkit.getLogger().info("This command can not be run in console.");
            }
        }
        return true;
    }
}
