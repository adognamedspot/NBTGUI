package com.adognamedspot.nbtgui.menusystem.menus;

import org.bukkit.event.inventory.InventoryClickEvent;

import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PaginatedMenu;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;

public class PlayerMenu extends PaginatedMenu {

    public PlayerMenu(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
    	String[] playerName = new String[] { NBTGUI.getPlugin().getData().getSelectedPlayer() };
        return Lang.PLAYER_MENU_TITLE.getString(playerName);
    }

    @Override
    public int getSlots() {
        return 54;
    }

	@Override
	public void handleMenu(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMenuItems() {
		// TODO Auto-generated method stub
		addTemplateBackClose();
		
	}


}
