package com.adognamedspot.nbtgui.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

	public static String parseColorString(String value) {
		return ChatColor.translateAlternateColorCodes('&', value);
	}
	
	public static List<String> parseColorString(List<String> value) {
		List<String> translated = new ArrayList<>();
	   	for (String entry : value){
	   		translated.add(ChatColor.translateAlternateColorCodes('&', entry));
	   	}

		return translated;
	}
	
	public static String stripColor(String value) {
		return ChatColor.stripColor(value);
	}

	public static ItemStack getItem(Material type, String name, List<String> lore, String skin) {
//	public static ItemStack getItem(Material type, String name, String lore, String skin) {
		return getItem(type, name, lore, skin, false);
	}
	
	public static ItemStack getItem(Material type, String name, List<String> lore, String skin, boolean b) {
//	public static ItemStack getItem(Material type, String name, String lore, String skin, boolean b) {
		ItemStack item = new ItemStack(type, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(parseColorString(name));
//		List<String> lorelist = new ArrayList<>();
//		lorelist.add(lore);
//		meta.setLore(lorelist);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

	public static boolean hasClickDelay(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Single string to ArrayList
	 * 
	 * @param lore
	 * @return
	 */
	public static List<String> lore(String lore) {
		List<String> lores = new ArrayList<String>();
		lores.add(lore);
		
		return lores;
	}

	/**
	 * Menu item for ItemMeta - Green = Meta, Red = No Meta
	 * 
	 * @param item
	 * @return Red or Green Item, depending on item's meta
	 */
	public static ItemStack rawNBTItem(ItemStack item) {
		if (item.hasItemMeta())
			return Utils.getItem(Material.GREEN_STAINED_GLASS_PANE, "Raw NBT Data", lore("Click to display NBT Data"), null);
		else
			return Utils.getItem(Material.RED_STAINED_GLASS_PANE, "Raw NBT Data", lore("No NBT Data found"), null);
	}

}
