package com.adognamedspot.nbtgui.api.utils;

import com.adognamedspot.nbtgui.api.NbtApiException;

public class CheckUtil {

    private CheckUtil() {
        // util
    }

    public static void assertAvailable(MinecraftVersion version) {
        if (!MinecraftVersion.isAtLeastVersion(version))
            throw new NbtApiException(
                    "This Method is only avaliable for the version " + version.name() + " and above!");
    }

}