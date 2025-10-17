package com.bossplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class BossPlugin extends JavaPlugin {
    private BossManager bossManager;
    
    @Override
    public void onEnable() {
        this.bossManager = new BossManager();
        getCommand("boss").setExecutor(new BossCommand(this));
        getServer().getPluginManager().registerEvents(new BossListener(this), this);
        getLogger().info("BossPlugin aktif!");
    }
    
    public BossManager getBossManager() {
        return bossManager;
    }
}