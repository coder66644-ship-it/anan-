package com.bossplugin;

public enum BossType {
    DRAGON(500, 50, "Ejderha"),
    GIANT(300, 30, "Dev"),
    WITHER_KING(400, 45, "Wither Kral");
    
    private int health;
    private int damage;
    private String displayName;
    
    BossType(int health, int damage, String displayName) {
        this.health = health;
        this.damage = damage;
        this.displayName = displayName;
    }
    
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public String getDisplayName() { return displayName; }
}