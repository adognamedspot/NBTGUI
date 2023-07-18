package com.adognamedspot.nbtgui.lists;

import com.adognamedspot.nbtgui.util.Utils;

public enum Lang {
    PREFIX("&9&lNBT GUI &5➔ &r"),
    NBTGUI_MENU_TITLE("&9&lNBT GUI"),
    INVENTORY_ENDERCHEST_MENU_TITLE("&7&l{0}"),
    PLAYER_ENDERCHEST_MENU_TITLE("&6{0} Ender Chest"),
    PLAYER_INVENTORY_MENU_TITLE("&6{0} Inventory"),
    PLAYER_MENU_TITLE("&7&l{0}"),
    ITEM_MENU_TITLE("&e&l{0}"),
    ERROR_PREVIOUS_PAGE("&cYou are already on the first page."),
    ERROR_NEXT_PAGE("&cYou are on the last page."),
    ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),
    ERROR_INVALID_MENU("&cInvalid Menu."),
    ERROR_FIRST_MENU("&cYou are already at the first menu."),
    ;

    private final String string;

    Lang(String string) {
    	this.string = string;
	}

	public String getString(String[] variables) {
        String value = string;
        if (variables == null || variables.length == 0) return Utils.parseColorString(value);
        for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
        return Utils.parseColorString(value);
    }

}