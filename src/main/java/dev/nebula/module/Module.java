package dev.nebula.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;

public class Module {
    protected static final Minecraft mc = Minecraft.getInstance();
    private String name;
    private String description;
    private Category category;
    private KeyBinding keyBinding;
    private boolean enabled;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keyBinding = new KeyBinding("key.nebula.module." + name.toLowerCase(),
                InputMappings.Type.KEYSYM,
                InputMappings.INPUT_INVALID.getKeyCode(),
                "key.categories.nebula"
        );
        this.enabled = false;
    }

    private int key;

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void tick() {
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {}
    public void onDisable() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public KeyBinding getKeyBinding() {
        return keyBinding;
    }

    public void setKeyBinding(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
}