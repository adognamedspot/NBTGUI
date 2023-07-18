package com.adognamedspot.nbtgui.menusystem;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected int maxItemsPerPage = 18;
    protected int index = 0;

    public PaginatedMenu(PlayerMU playerMU) {
        super(playerMU);
    }

    public int getMaxItemsPerPage() {
    	return maxItemsPerPage;
    }
    
    public void addTemplatePrevNext(){
        inventory.setItem(48, super.previousPageItem);
        inventory.setItem(50, super.nextPageItem);

        for (int i = 0; i < 9; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

        for (int i = 45; i < 54; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }
    }
    
    public void addTemplateBack() {
    	inventory.setItem(49, super.backItem);

        for (int i = 0; i < 54; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

    }

    public void addTemplateBackClose() {
    	inventory.setItem(48, super.backItem);
    	inventory.setItem(50, super.closeItem);

        for (int i = 45; i < 54; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

    }

    public void addTemplatePlayerInventory() {
    	inventory.setItem(49, super.backItem);

    	inventory.setItem(36, super.fillerGlass);
    	inventory.setItem(37, super.fillerGlass);
    	inventory.setItem(38, super.fillerGlass);
    	inventory.setItem(40, super.fillerGlass);

    	for (int i = 45; i < 54; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

    }

    public void addMenuPlayerEnder() {
    	inventory.setItem(49, super.backItem);

        for (int i = 0; i < 9; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

    	for (int i = 36; i < 54; i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, super.fillerGlass);
        }

    }
}