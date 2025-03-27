package dev.nebula.client;

import dev.nebula.client.event.EventManager;
import dev.nebula.module.ModuleManager;
import dev.nebula.module.Setting;
import net.minecraft.client.Minecraft;

public class Nebula {
    public static final String NAME = "Nebula";
    public static final String VERSION = "1.0";
    public static Nebula INSTANCE;
    public static final Minecraft mc = Minecraft.getInstance();

    private ModuleManager moduleManager;
    private EventManager eventManager;
    private Setting setting;

    public void startup() {
        INSTANCE = this;
        moduleManager = new ModuleManager();
        eventManager = new EventManager();
        setting = new Setting("DefaultSetting", "default"); // Добавляем необходимые параметры
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public Setting getSetting() {
        return setting;
    }
}