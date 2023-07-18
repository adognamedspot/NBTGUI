package com.adognamedspot.nbtgui.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.menus.*;

import java.util.UUID;

public class PlayerMU {

    private final UUID owner;
    private int NBTPage;
    
    private int menu_loc;
    private String[] menu_page;

    public PlayerMU(UUID uuid) {
		// TODO Auto-generated constructor stub
    	this.owner = uuid;
    	this.menu_loc = -1;
    	this.menu_page = new String[10];
	}

    /**
     * Gets page number for <- Prev / Next -> GUIs
     * 
     * @return Page number for pagination
     */
    public int getNBTPage() {
    	return this.NBTPage;
    }
    
    /**
     * Sets page number for <- Prev / Next -> GUIs
     * 
     * @param page - Current page number
     */
    public void setNBTPage(int page) {
    	this.NBTPage = page;
    }
    
    /**
     * Gets owner of GUI
     * 
     * @return GUI owner
     */
	public Player getOwner() {
        return Bukkit.getPlayer(owner);
    }
	
	/**
	 *  Resets Menu Location for <- Back / Next ->
	 */
	public void resetMenuLoc() {
		this.menu_loc = -1;
	}
	
	/**
	 * Opens a menu and keeps track of the sequence for <- Back / Next ->
	 * 
	 * @param menu - Name of the Menu to open
	 */
	public void openMenu(String menu) {
		this.menu_loc++;
		this.menu_page[this.menu_loc] = menu;
		switch (menu) {
		case "NBTMenu":
			new NBTMenu(this).open();
			break;
		case "InventoryEnderMenu":
			new InventoryEnderMenu(this).open();
			break;
		case "PlayerInventoryMenu":
			new PlayerInventoryMenu(this).open();
			break;
		case "PlayerEnderMenu":
			new PlayerEnderMenu(this).open();
			break;
		case "PlayerMenu":
			new PlayerMenu(this).open();
			break;
		case "ItemMenu":
			new ItemMenu(this).open();
			break;
		default:
			this.getOwner().sendMessage(Lang.PREFIX.getString(null) + Lang.ERROR_INVALID_MENU.getString(null));
			return;
		}
		Menu.playClickSound(this.getOwner());
	}
	
	/**
	 * Opens the last menu [<- Back]
	 */
	public void backMenu() {
		if (this.menu_loc == 0) {
			this.getOwner().sendMessage(Lang.PREFIX.getString(null) + Lang.ERROR_FIRST_MENU.getString(null));
		} else {
			this.menu_loc--;
			this.openMenu(this.menu_page[this.menu_loc]);
			this.menu_loc--;
		}
	}
	
}