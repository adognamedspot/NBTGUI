package com.adognamedspot.nbtgui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.adognamedspot.nbtgui.menusystem.PlayerMU;
import com.adognamedspot.nbtgui.util.Utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Data {

    protected String SelectedPlayer = null;
    
    protected String SelectedUUID = null;
    
    protected ItemStack SelectedItem = null;

    private final List<String> PlayerHeadUUIDList = new ArrayList<>();

    private final List<ItemStack> PlayerHeadItems = new ArrayList<>();

    private final ConcurrentHashMap<UUID, PlayerMU> playerMUList = new ConcurrentHashMap<>();

    public String getSelectedPlayer() {
    	return this.SelectedPlayer;
    }
    
    public void setSelectedPlayer(String player) {
    	this.SelectedPlayer = player;
    }
    
    public String getSelectedUUID() {
    	return this.SelectedUUID;
    }
    
    public void setSelectedUUID(String uuid) {
    	this.SelectedUUID = uuid;
    }
    
    public ItemStack getSelectedItem() {
    	return this.SelectedItem;
    }
    
    public void setSelectedItem(ItemStack item) {
    	this.SelectedItem = item;
    }
    
    public List<String> getPlayerHeadList() {
    	return this.PlayerHeadUUIDList;
    }
    
    public List<ItemStack> getPlayerHeads() {
    	return this.PlayerHeadItems;
    }
    
    public PlayerMU getPlayerMU(UUID uuid) {
        if (playerMUList.containsKey(uuid)) {
            return playerMUList.get(uuid);
        } else {
            PlayerMU pmu = new PlayerMU(uuid);
            playerMUList.put(uuid, pmu);
            return pmu;
        }
    }

    public void loadData() {
    	
    	processOfflinePlayers();

    }
    
    public void processOfflinePlayers() {
    	this.PlayerHeadUUIDList.clear();
    	this.PlayerHeadItems.clear();
    	ArrayList<String> lore = new ArrayList<>();
    	for (OfflinePlayer offplayer : Bukkit.getOfflinePlayers()){
    		if (offplayer.isOnline()) {
    			lore.clear();
    			lore.add(offplayer.getUniqueId().toString());
    			lore.add("&a&lOnline");
    			this.PlayerHeadUUIDList.add(offplayer.getUniqueId().toString());
    			this.PlayerHeadItems.add(buildPlayerHead(offplayer, lore));
    		}
    	}
    	for (OfflinePlayer offplayer : Bukkit.getOfflinePlayers()){
    		if (!this.PlayerHeadUUIDList.contains(offplayer.getUniqueId().toString())) {
    			lore.clear();
    			lore.add(offplayer.getUniqueId().toString());
    			lore.add("&c&lOffline");
    			this.PlayerHeadUUIDList.add(offplayer.getUniqueId().toString());
    			this.PlayerHeadItems.add(buildPlayerHead(offplayer, lore));
    		}
    	}
    }
    
    public ItemStack buildPlayerHead(OfflinePlayer offplayer, String lore) {
    	List<String> lores = new ArrayList<>();
    	lores.add(lore);
    	return buildPlayerHead(offplayer, lores);
    }
    
    public ItemStack buildPlayerHead(OfflinePlayer offplayer, List<String> lore) {
		ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
		meta.setDisplayName(offplayer.getName());
		meta.setLore(Utils.parseColorString(lore));
		meta.setOwningPlayer(offplayer);
		playerHead.setItemMeta(meta);
		return playerHead;
    }
}