package dev.nebula.client;

import dev.nebula.module.ModuleManager;
import net.minecraft.client.Minecraft;

public class Nebula {
    public static final String NAME = "Nebula";
    public static final String VERSION = "1.0";
    public static Nebula INSTANCE;
    public static final Minecraft mc = Minecraft.getInstance();

    private ModuleManager moduleManager;

    public void startup() {
        INSTANCE = this;
        moduleManager = new ModuleManager();
    }

    public void onTick() {
        if(moduleManager != null) {
            moduleManager.tick();
        }
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }
}