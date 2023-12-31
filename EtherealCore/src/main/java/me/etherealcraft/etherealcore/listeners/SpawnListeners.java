package me.etherealcraft.etherealcore.listeners;

import me.etherealcraft.etherealcore.EtherealCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class SpawnListeners implements Listener {

    private final EtherealCore plugin;

    public SpawnListeners(EtherealCore plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        //when a player joins for the first time, teleport them to the spawn if it is set
        if(!e.getPlayer().hasPlayedBefore()){
            Location location = plugin.getConfig().getLocation("Spawns." + String.valueOf(e.getPlayer().getLocation().getWorld().getName()));
            if(location != null){
                //spawn them
                e.getPlayer().teleport(location);
            }
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent e){
        //when the player dies, respawn them at the spawn location if set
        Location location = plugin.getConfig().getLocation("Spawns." + String.valueOf(e.getPlayer().getLocation().getWorld().getName()));
        if(location != null){
            //spawn them
            e.setRespawnLocation(location);
        }
    }

}
