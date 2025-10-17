package com.bossplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class BossManager {
    private HashMap<UUID, CustomBoss> activeBosses;
    
    public BossManager() {
        this.activeBosses = new HashMap<>();
    }
    
    public void spawnBoss(Player player, BossType type) {
        Location spawnLoc = player.getLocation().add(10, 0, 10);
        CustomBoss boss = new CustomBoss(type, spawnLoc);
        activeBosses.put(boss.getBossId(), boss);
        boss.spawn();
    }
    
    public void removeBoss(UUID bossId) {
        activeBosses.remove(bossId);
    }
    
    public CustomBoss getBoss(UUID bossId) {
        return activeBosses.get(bossId);
    }
}