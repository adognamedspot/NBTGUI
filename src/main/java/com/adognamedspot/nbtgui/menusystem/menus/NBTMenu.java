package com.adognamedspot.nbtgui.menusystem.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import com.adognamedspot.nbtgui.Data;
import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PaginatedMenu;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;

public class NBTMenu extends PaginatedMenu {

    public NBTMenu(PlayerMU pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return Lang.NBTGUI_MENU_TITLE.getString(null);
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        NBTGUI plugin = NBTGUI.getPlugin();
        Data data = plugin.getData();

        Player player = pmu.getOwner();

        ItemStack clickedItem = e.getCurrentItem();
 
        if (clickedItem == null) return;
        if (clickedItem.getType().equals(Material.AIR)) return;
        if (clickedItem.equals(fillerGlass)) return;
        ItemMeta clickedMeta = clickedItem.getItemMeta();

        if (e.getClickedInventory() == player.getInventory()) {
        	data.setSelectedItem(clickedItem);
        	pmu.openMenu("ItemMenu");
        } else
        if (clickedItem.getType().equals(Material.PLAYER_HEAD)) {
//        if (data.getPlayerHeadList().contains(clickedMeta.getLore().get(0))) {
        	data.setSelectedPlayer(clickedMeta.getDisplayName());
        	data.setSelectedUUID(clickedMeta.getLore().get(0));
        	pmu.openMenu("InventoryEnderMenu");
        }

        if (clickedItem.equals(previousPageItem)) {
            if (page == 0) {
                player.sendMessage(Lang.PREFIX.getString(null) + Lang.ERROR_PREVIOUS_PAGE.getString(null));
            } else {
                page = page - 1;
                pmu.setNBTPage(page);
                super.open();
                playClickSound(player);
            }
            //next page
        } else if (clickedItem.equals(nextPageItem)) {

            if (!((index + 1) >= OfflinePlayers.size())) {
                page = page + 1;
                pmu.setNBTPage(page);
                super.open();
                playClickSound(player);
            } else player.sendMessage(Lang.PREFIX.getString(null) + Lang.ERROR_NEXT_PAGE.getString(null));
        }
        
        
    }

    @Override
    public void setMenuItems() {

        page = pmu.getNBTPage();

        addTemplatePrevNext();
        
        inventory.setItem(4, nbtInfo);

        //pagination loop
        if(!OfflinePlayers.isEmpty()) {
            for(int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= OfflinePlayers.size()) break;
                if (OfflinePlayers.get(index) != null) {
                        ItemStack theItem = OfflinePlayers.get(index);
                        inventory.addItem(theItem);
                }
            }
        }
        
    }


}
