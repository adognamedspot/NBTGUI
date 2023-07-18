package com.adognamedspot.nbtgui.lists;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.adognamedspot.nbtgui.util.Utils;

public enum MenuItem {

    NBT_INFO(Material.DRAGON_BREATH, "Select a user or pick from inventory", null, null),
    ENDER_CHEST(Material.ENDER_CHEST, "Pick from Ender Chest", null, null),
    INVENTORY(Material.CHEST, "Pick from Inventory", null, null),
	FILLER_GLASS(Material.BLACK_STAINED_GLASS_PANE, "&r", null, null),
    CLOSE(Material.BARRIER, "&c&lClose", null, null),
    NEXT_PAGE(Material.PAPER, "&eNext Page >", null, null),
    PREVIOUS_PAGE(Material.PAPER, "&e< Previous Page", null, null),
    BACK(Material.STRUCTURE_VOID, "&6&l<- Back", null, null),
    GOOD_NBT(Material.GREEN_STAINED_GLASS_PANE, "NBT Data", null, null),
    NO_NBT(Material.RED_STAINED_GLASS_PANE, "No NBT Data", null, null),
    ;
	
    private final Material type;
    private final String name;
//    private final String lore;
    private ArrayList<String> lore = new ArrayList<>();
    private final String skin;

    MenuItem(Material type, String name, ArrayList<String> lore, String skin) {
//    MenuItem(Material type, String name, String lore, String skin) {
		// TODO Auto-generated constructor stub
    	this.type = type;
    	this.name = name;
    	this.lore = lore;
    	this.skin = skin;
	}

    public ItemStack getItem() {
        return Utils.getItem(this.type, this.name, this.lore, this.skin, true);
    }
}