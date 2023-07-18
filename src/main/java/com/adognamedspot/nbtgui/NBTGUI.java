package com.adognamedspot.nbtgui;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.adognamedspot.nbtgui.commands.NBTGUICMD;
import com.adognamedspot.nbtgui.commands.NBTGUITab;
import com.adognamedspot.nbtgui.listeners.MenuListener;

public final class NBTGUI extends JavaPlugin implements Listener {
	
	private Data data;
	
	@Override
    public void onEnable() {
    	// TODO
        this.data = new Data();
        
		registerCommands();
		registerListeners();
    }

    @Override
    public void onDisable() {
    	// TODO
    }

    public Data getData() {
    	return this.data;
    }
	
    public static NBTGUI getPlugin() {
        return NBTGUI.getPlugin(NBTGUI.class);
    }
    
    private void registerCommands() {
        getCommand("nbtgui").setExecutor(new NBTGUICMD());
        getCommand("nbtgui").setTabCompleter(new NBTGUITab());
    }
    
    private void registerListeners() {
    	getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

}