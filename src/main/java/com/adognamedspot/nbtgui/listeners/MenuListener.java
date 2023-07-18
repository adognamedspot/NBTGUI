package com.adognamedspot.nbtgui.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import com.adognamedspot.nbtgui.menusystem.Menu;
import com.adognamedspot.nbtgui.util.Utils;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            if (Utils.hasClickDelay(player)) return;
            menu.handleMenu(e);
        }
    }
    @EventHandler
    public void onMenuClose(InventoryClickEvent e) {
    	Bukkit.getLogger().warning("Window Closed");
    }
}