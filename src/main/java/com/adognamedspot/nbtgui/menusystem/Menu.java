package com.adognamedspot.nbtgui.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.MenuItem;

import java.util.List;

public abstract class Menu implements InventoryHolder {

    public Menu(PlayerMU pmu) {
        this.pmu = pmu;
    }

    protected PlayerMU pmu;
    protected Inventory inventory;
    protected ItemStack nbtInfo = MenuItem.NBT_INFO.getItem();
    protected ItemStack enderChest = MenuItem.ENDER_CHEST.getItem();
    protected ItemStack inventoryChest = MenuItem.INVENTORY.getItem();
    protected ItemStack fillerGlass = MenuItem.FILLER_GLASS.getItem();
    protected ItemStack backItem = MenuItem.BACK.getItem();
    protected ItemStack nextPageItem = MenuItem.NEXT_PAGE.getItem();
    protected ItemStack previousPageItem = MenuItem.PREVIOUS_PAGE.getItem();
    protected ItemStack closeItem = MenuItem.CLOSE.getItem();
    
    protected List<ItemStack> OfflinePlayers = OfflinePlayers();
    
    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());
        this.setMenuItems();
        pmu.getOwner().openInventory(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, fillerGlass);
            }
        }
    }
    
    private List<ItemStack> OfflinePlayers() {
        NBTGUI plugin = NBTGUI.getPlugin();
        return plugin.getData().getPlayerHeads();
    }

    public static void playClickSound(Player player) {
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, (float) 0.5, (float) 1);
    }

    public void playClickOnSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, (float) 0.5, (float) 1);
    }

    public void playClickOffSound(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_OFF, (float) 0.5, (float) 1);
    }
}