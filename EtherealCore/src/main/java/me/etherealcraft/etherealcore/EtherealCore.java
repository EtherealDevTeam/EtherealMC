package me.etherealcraft.etherealcore;

import me.etherealcraft.etherealcore.commands.SetHomeCommand;
import me.etherealcraft.etherealcore.files.HomesConfig;
import me.etherealcraft.etherealcore.files.SpawnsConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.etherealcraft.etherealcore.commands.SetSpawnCommand;
import me.etherealcraft.etherealcore.commands.SpawnCommand;
import me.etherealcraft.etherealcore.listeners.SpawnListeners;

public final class EtherealCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("EtherealCore has started.");
        //Setup/Load Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //register the commands
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        //getCommand("sethome").setExecutor(new SetHomeCommand(this));

        //register the events
        getServer().getPluginManager().registerEvents(new SpawnListeners(this), this);

        //setup config.yml
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //setup spawns.yml
        SpawnsConfig.setup();
        SpawnsConfig.get().addDefault("Taco", "Rice");
        SpawnsConfig.get().options().copyDefaults(true);
        SpawnsConfig.save();

        //setup homes.yml
        HomesConfig.setup();
        HomesConfig.get().addDefault("Taco", "Rice");
        HomesConfig.get().options().copyDefaults(true);
        HomesConfig.save();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("EtherealCore has stopped.");
    }
}
