package me.etherealcraft.etherealcore.files;

import me.etherealcraft.etherealcore.EtherealCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class PlayerDataConfig {
    private static File file;
    private static FileConfiguration customFile;
    private final EtherealCore plugin;
    public PlayerDataConfig(EtherealCore plugin){
        this.plugin = plugin;
    }
    public static String playerUUID;
    //finds or generates the spawns config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("EtherealCore").getDataFolder(), "/PlayerData/" + playerUUID + ".yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
                //blank
            }

        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try{
            customFile.save(file);
        }catch (IOException e){
            Bukkit.getLogger().info("Couldn't save file");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
