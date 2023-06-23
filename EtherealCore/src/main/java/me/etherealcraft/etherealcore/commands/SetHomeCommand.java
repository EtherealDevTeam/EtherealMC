package me.etherealcraft.etherealcore.commands;

import me.etherealcraft.etherealcore.EtherealCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final EtherealCore etherealCore;

    public SetHomeCommand(EtherealCore etherealCore) {this.etherealCore = etherealCore;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        //Make sure that they are a player
        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("etherealcore.commands.sethome")) {
                //get the players location
                Location location = player.getLocation();
                if(args.length == 0) {
                    //set the spawn location in the config.yml
                    etherealCore.getConfig().set(String.valueOf(player.getName()), location);

                    //save the config.yml
                    etherealCore.saveConfig();

                    //send a message to the player
                    player.sendMessage("Spawn location set!");
                }else{
                    String world = args[0];

                    etherealCore.getConfig().set(player.getName(), location);
                    etherealCore.saveConfig();
                }
            }else{
                player.sendMessage("You do not have the required permission (etherealcore.commands.setspawn)");
            }
        }
        else{
            Bukkit.getLogger().info("This command can not be run in console.");
        }

        return true;
    }


}
