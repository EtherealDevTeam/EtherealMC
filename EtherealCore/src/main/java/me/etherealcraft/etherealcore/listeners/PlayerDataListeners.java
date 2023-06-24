package me.etherealcraft.etherealcore.listeners;

import me.etherealcraft.etherealcore.EtherealCore;
import me.etherealcraft.etherealcore.files.PlayerDataConfig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDataListeners implements Listener {

    private final EtherealCore plugin;

    public PlayerDataListeners(EtherealCore plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        PlayerDataConfig.playerUUID = String.valueOf(e.getPlayer().getUniqueId());
        //setup playerdata.yml
        PlayerDataConfig.setup();
        PlayerDataConfig.get().addDefault("Name", String.valueOf(e.getPlayer().getName()));
        PlayerDataConfig.get().addDefault("UUID", String.valueOf(e.getPlayer().getUniqueId()));
        PlayerDataConfig.get().addDefault("IP", String.valueOf(e.getPlayer().getAddress().getHostName()));
        PlayerDataConfig.get().options().copyDefaults(true);
        PlayerDataConfig.save();
    }
}
