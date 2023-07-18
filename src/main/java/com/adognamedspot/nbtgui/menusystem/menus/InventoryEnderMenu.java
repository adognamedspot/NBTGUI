package com.adognamedspot.nbtgui.menusystem.menus;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.adognamedspot.nbtgui.Data;
import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PaginatedMenu;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;
import com.adognamedspot.nbtgui.util.Utils;

public class InventoryEnderMenu extends PaginatedMenu {

    public InventoryEnderMenu(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
    	String[] playerName = new String[] { NBTGUI.getPlugin().getData().getSelectedPlayer() };
        return Lang.INVENTORY_ENDERCHEST_MENU_TITLE.getString(playerName);
    }

    @Override
    public int getSlots() {
        return 54;
    }

	@Override
	public void handleMenu(InventoryClickEvent e) {
		ItemStack clickedItem = e.getCurrentItem();
		Player player = pmu.getOwner();
		
        if (clickedItem == null) return;
        if (clickedItem.getType().equals(Material.AIR)) return;
        if (clickedItem.equals(fillerGlass)) return;
        if (e.getClickedInventory() == player.getInventory()) return;
        
        if (clickedItem.getType().equals(Material.PLAYER_HEAD)) {
        	pmu.openMenu("PlayerMenu");
        }
        
        switch (Utils.stripColor(clickedItem.getItemMeta().getDisplayName())) {
        case "Pick from Inventory":
        	pmu.openMenu("PlayerInventoryMenu");
        	break;
        case "Pick from Ender Chest":
        	pmu.openMenu("PlayerEnderMenu");
        	break;
        case "<- Back":
        	pmu.backMenu();
        }
        
		
	}

	@Override
	public void setMenuItems() {
		Data data = NBTGUI.getPlugin().getData();
        addTemplateBack();
        
        UUID uuid = UUID.fromString(data.getSelectedUUID());
        OfflinePlayer off = Bukkit.getOfflinePlayer(uuid);
        
        inventory.setItem(13, data.buildPlayerHead(off, data.getSelectedUUID()));
        inventory.setItem(20, inventoryChest);
        inventory.setItem(24, enderChest);
//        inventory.setItem(20, new ItemStack(inventoryChest));
//        inventory.setItem(24, new ItemStack(enderChest));
		
	}


}
