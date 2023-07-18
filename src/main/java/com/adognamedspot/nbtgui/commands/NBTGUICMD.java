package com.adognamedspot.nbtgui.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import com.adognamedspot.nbtgui.Data;
import com.adognamedspot.nbtgui.NBTGUI;
import com.adognamedspot.nbtgui.lists.Lang;
import com.adognamedspot.nbtgui.menusystem.PlayerMU;

public class NBTGUICMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        NBTGUI plugin = NBTGUI.getPlugin();
        Data data = plugin.getData();

        if (sender instanceof Player player) {
        	data.loadData();
            PlayerMU pmu = data.getPlayerMU(player.getUniqueId());
            pmu.resetMenuLoc();
            pmu.openMenu("NBTMenu");
            player.playSound(player.getLocation(), Sound.ENTITY_LLAMA_SWAG, 1, 1);
        } else sender.sendMessage(Lang.PREFIX.getString(null) + Lang.ERROR_NOT_CONSOLE_COMMAND.getString(null));        return true;
    }
}