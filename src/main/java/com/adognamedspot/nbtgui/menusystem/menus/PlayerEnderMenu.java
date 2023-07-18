package com.adognamedspot.nbtgui.menusystem.menus;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.adognamedspot.nbtgui.Data;
import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PaginatedMenu;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;
import com.adognamedspot.nbtgui.util.Utils;

public class PlayerEnderMenu extends PaginatedMenu {

    public PlayerEnderMenu(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
    	String[] playerName = new String[] { NBTGUI.getPlugin().getData().getSelectedPlayer() };
        return Lang.PLAYER_ENDERCHEST_MENU_TITLE.getString(playerName);
    }

    @Override
    public int getSlots() {
        return 54;
    }

	@Override
	public void handleMenu(InventoryClickEvent e) {
		Data data = NBTGUI.getPlugin().getData();
		ItemStack clickedItem = e.getCurrentItem();
		Player player = pmu.getOwner();
		
        if (clickedItem == null) return;
        if (clickedItem.getType().equals(Material.AIR)) return;
        if (clickedItem.equals(fillerGlass)) return;
        if (e.getClickedInventory() == player.getInventory()) return;
        if (Utils.stripColor(clickedItem.getItemMeta().getDisplayName()).equals("<- Back")) {
        	pmu.backMenu();
        } else {
        	data.setSelectedItem(clickedItem);
        	pmu.openMenu("ItemMenu");
        }
		
	}

	@Override
	public void setMenuItems() {
		addMenuPlayerEnder();
		Data data = NBTGUI.getPlugin().getData();
        
        UUID uuid = UUID.fromString(data.getSelectedUUID());
        OfflinePlayer off = Bukkit.getOfflinePlayer(uuid);
        if (off.isOnline()) {
        	if(Bukkit.getEntity(uuid) instanceof Player){
        		Player p = (Player) Bukkit.getEntity(uuid);
        		Inventory inv = p.getEnderChest();

        		ItemStack[] is = inv.getStorageContents();
        		int ii = 0;
        		for (int i = 0; i < is.length; i++) {
        			ii = i + 9;
        			if (is[i] != null) {
        				inventory.setItem(ii, is[i]);
        			} else {
        				inventory.setItem(ii, null);
        			}
        		}
        	}
        }
		
	}


}
