package com.bossplugin;

import org.bukkit.Location;
import org.bukkit.entity.Wither;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import java.util.UUID;

public class CustomBoss {
    private UUID bossId;
    private BossType type;
    private Location location;
    private LivingEntity entity;
    private int currentPhase;
    
    public CustomBoss(BossType type, Location location) {
        this.bossId = UUID.randomUUID();
        this.type = type;
        this.location = location;
        this.currentPhase = 1;
    }
    
    public void spawn() {
        switch (type) {
            case DRAGON:
                entity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.ENDER_DRAGON);
                break;
            case GIANT:
                entity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.GIANT);
                break;
            case WITHER_KING:
                entity = (LivingEntity) location.getWorld().spawnEntity(location, EntityType.WITHER);
                break;
        }
        
        entity.setCustomName("§6" + type.getDisplayName() + " §c[" + type.getHealth() + "❤]");
        entity.setCustomNameVisible(true);
        entity.setMaxHealth(type.getHealth());
        entity.setHealth(type.getHealth());
        
        setupEquipment();
        startAbilityCycle();
    }
    
    private void setupEquipment() {
        if (entity.getEquipment() != null) {
            entity.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
            entity.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        }
    }
    
    private void startAbilityCycle() {
        entity.getServer().getScheduler().runTaskTimer((org.bukkit.plugin.Plugin) entity.getServer().getPluginManager().getPlugin("BossPlugin"), () -> {
            if (entity.isDead()) return;
            
            performRandomAbility();
            updateBossBar();
            
        }, 0L, 100L);
    }
    
    private void performRandomAbility() {
        double random = Math.random();
        
        if (random < 0.3) {
            lightningStrike();
        } else if (random < 0.6) {
            summonMinions();
        } else {
            explosiveAttack();
        }
    }
    
    private void lightningStrike() {
        location.getWorld().strikeLightning(entity.getLocation());
    }
    
    private void summonMinions() {
        for (int i = 0; i < 3; i++) {
            Location minionLoc = entity.getLocation().add(Math.random() * 5, 0, Math.random() * 5);
            minionLoc.getWorld().spawnEntity(minionLoc, EntityType.ZOMBIE);
        }
    }
    
    private void explosiveAttack() {
        location.getWorld().createExplosion(entity.getLocation(), 2.0f, false);
    }
    
    private void updateBossBar() {
        int currentHealth = (int) entity.getHealth();
        entity.setCustomName("§6" + type.getDisplayName() + " §c[" + currentHealth + "❤]");
    }
    
    public UUID getBossId() { return bossId; }
    public LivingEntity getEntity() { return entity; }
    public BossType getType() { return type; }
}