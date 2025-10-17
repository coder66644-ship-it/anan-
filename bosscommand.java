package com.bossplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BossCommand implements CommandExecutor {
    private BossPlugin plugin;
    
    public BossCommand(BossPlugin plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Sadece oyuncular kullanabilir!");
            return true;
        }
        
        Player player = (Player) sender;
        
        if (args.length == 0) {
            player.sendMessage("§cKullanım: /boss spawn <boss tipi>");
            player.sendMessage("§6Boss Tipleri: dragon, giant, witherking");
            return true;
        }
        
        if (args[0].equalsIgnoreCase("spawn")) {
            if (args.length < 2) {
                player.sendMessage("§cBoss tipi belirtin: dragon, giant, witherking");
                return true;
            }
            
            BossType type = parseBossType(args[1]);
            if (type == null) {
                player.sendMessage("§cGeçersiz boss tipi!");
                return true;
            }
            
            plugin.getBossManager().spawnBoss(player, type);
            player.sendMessage("§a" + type.getDisplayName() + " spawn edildi!");
        }
        
        return true;
    }
    
    private BossType parseBossType(String type) {
        switch (type.toLowerCase()) {
            case "dragon": return BossType.DRAGON;
            case "giant": return BossType.GIANT;
            case "witherking": return BossType.WITHER_KING;
            default: return null;
        }
    }
}