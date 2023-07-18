package com.adognamedspot.nbtgui.menusystem.menus;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.api.NBTCompoundList;
import com.adognamedspot.nbtgui.api.NBTItem;
import com.adognamedspot.nbtgui.api.NBTList;
import com.adognamedspot.nbtgui.api.iface.ReadWriteNBT;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PaginatedMenu;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;
import com.adognamedspot.nbtgui.util.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemMenu extends PaginatedMenu {
	
	public ItemStack CurrentItem = NBTGUI.getPlugin().getData().getSelectedItem();
	public boolean hasMeta = CurrentItem.hasItemMeta();
	public ItemMeta CurrentMeta = CurrentItem.getItemMeta();
	public int Amount = CurrentItem.getAmount();
	
	NBTItem nbti = new NBTItem(CurrentItem);
	
    public ItemMenu(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return Lang.ITEM_MENU_TITLE.getString(new String[] { CurrentItem.getType().name() });
    }

    @Override
    public int getSlots() {
        return 54;
    }

	@Override
	public void handleMenu(InventoryClickEvent e) {
		// TODO Auto-generated method stub
		ItemStack clickedItem = e.getCurrentItem();
		
        if (clickedItem == null) return;
        if (clickedItem.getType().equals(Material.AIR)) return;
        if (clickedItem.equals(fillerGlass)) return;
        if (Utils.stripColor(clickedItem.getItemMeta().getDisplayName()).equals("<- Back")) pmu.backMenu();
        
        switch (Utils.stripColor(clickedItem.getItemMeta().getDisplayName())) {
        case "Display Name":
        	break;
        case "Raw NBT Data":
        	System.out.println(nbti);
//        	pmu.getOwner().sendMessage(Meta.toString());
//        	Bukkit.getLogger().warning(Meta.toString());
        	playClickSound(pmu.getOwner());
        	break;
        }
        
        
	}

	@Override
	public void setMenuItems() {
		// TODO Auto-generated method stub
//		ItemMeta CurrentMeta = CurrentItem.getItemMeta();
		addTemplateBackClose();
		
		// CurrentItem Selected
		inventory.setItem(0, CurrentItem);

		// Display Name
		inventory.setItem(2, Utils.getItem(Material.NAME_TAG, "Display Name", Utils.lore(Utils.parseColorString("&b" + CurrentMeta.getDisplayName())), null));

		// Lore
		inventory.setItem(3, Utils.getItem(Material.BOOK, "Item Lore", CurrentMeta.getLore(), null));

		// Raw NBT
		inventory.setItem(8, Utils.rawNBTItem(CurrentItem));
		
		// Damage
		inventory.setItem(5, Utils.getItem(Material.TNT, "Damage", Utils.lore(Utils.parseColorString("&6" + nbti.getInteger("Damage").toString())), null));
		
		// Repair Cost
		inventory.setItem(6, Utils.getItem(Material.ANVIL, "Repair Cost", Utils.lore(Utils.parseColorString("&6" + nbti.getInteger("RepairCost").toString())), null));
		
		// Item Flags
		setItemFlags(18);
		
		// Enchants
		setEnchants(27);
		
		
	}

	private void setItemFlags(int index) {
		List<String> lore = new ArrayList<>();
		String[] flag_desc = new String[10];
		flag_desc[0] = "&eArmor trim from leather armor";
		flag_desc[1] = "&eAttributes like Damage";
		flag_desc[2] = "&eWhat the ItemStack can break/destroy";
		flag_desc[3] = "&eDyes from colored leather armor";
		flag_desc[4] = "&eEnchants";
		flag_desc[5] = "&eWhere this ItemStack can be build/placed on";
		flag_desc[6] = "&ePotion effects, book and firework information, map tooltips,";
		flag_desc[7] = "&epatterns of banners, and enchantments of enchanted books";
		flag_desc[8] = "&eThe unbreakable State";
		
		lore.add(""); lore.add(""); lore.add(""); 		
		
		lore.set(1, Utils.parseColorString(flag_desc[0]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_ARMOR_TRIM)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_ARMOR_TRIM", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_ARMOR_TRIM", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[1]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_ATTRIBUTES", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_ATTRIBUTES", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[2]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_DESTROYS", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_DESTROYS", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[3]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_DYE)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_DYE", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_DYE", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[4]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_ENCHANTS", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_ENCHANTS", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[5]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_PLACED_ON", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_PLACED_ON", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[8]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE))  {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_UNBREAKABLE", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_UNBREAKABLE", lore, null));
		}
		index++;
		lore.set(1, Utils.parseColorString(flag_desc[6]));
		lore.set(2, Utils.parseColorString(flag_desc[7]));
		if (CurrentMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)) {
			lore.set(0, Utils.parseColorString("&aTrue"));
			inventory.setItem(index, Utils.getItem(Material.GREEN_BANNER, "HIDE_POTION_EFFECTS", lore, null));
		} else {
			lore.set(0, Utils.parseColorString("&cFalse"));
			inventory.setItem(index, Utils.getItem(Material.RED_BANNER, "HIDE_POTION_EFFECTS", lore, null));
		}
	}
	
	private void setEnchants(Integer index) {
		if (CurrentItem.getType().equals(Material.ENCHANTED_BOOK)) {
			setBookEnchants(index);
		} else {
			setItemEnchants(index);
		}
	}
	
	private void setBookEnchants(Integer index) {
		NBTList<ReadWriteNBT> list = nbti.getCompoundList("StoredEnchantments");
		ReadWriteNBT entry;
		for (int i = 0; i < list.size(); i++) {
			entry = list.get(i);
			inventory.setItem(index, Utils.getItem(Material.ENCHANTED_BOOK, entry.getString("id"), Utils.lore(Utils.parseColorString("&fLevel: " + entry.getShort("lvl"))), null));
			index++;
		}
	}
	
	private void setItemEnchants(Integer index) {
		Map<Enchantment,Integer> enchants = CurrentMeta.getEnchants();
		List<String> lore = new ArrayList<>();
		
		for (Map.Entry<Enchantment,Integer> entry: enchants.entrySet()) {
			lore.clear();
			lore.add("&fLevel: " + entry.getValue());
			inventory.setItem(index,Utils.getItem(Material.ENCHANTED_BOOK, entry.getKey().getKey().getNamespace() + ":" + entry.getKey().getKey().getKey(), Utils.parseColorString(lore), null));
			index++;
		}
	}
}
