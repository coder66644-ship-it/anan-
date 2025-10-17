package com.bossplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class BossListener implements Listener {
    private BossPlugin plugin;
    
    public BossListener(BossPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBossDeath(EntityDeathEvent event) {
        if (!(event.getEntity() instanceof org.bukkit.entity.LivingEntity)) return;
        
        org.bukkit.entity.LivingEntity entity = event.getEntity();
        
        if (entity.getCustomName() != null && entity.getCustomName().contains("Boss")) {
            Player killer = entity.getKiller();
            if (killer != null) {
                killer.sendMessage("§6Tebrikler! Boss'u yendin!");
                killer.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
                killer.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
            }
            
            event.getDrops().clear();
            event.setDroppedExp(100);
        }
    }
}