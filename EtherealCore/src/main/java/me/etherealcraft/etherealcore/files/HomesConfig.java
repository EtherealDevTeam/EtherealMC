package me.etherealcraft.etherealcore.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HomesConfig {
    private static File file;
    private static FileConfiguration customFile;

    //finds or generates the spawns config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("EtherealCore").getDataFolder(), "homes.yml");

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
