package dev.nebula.module;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules;
    private boolean wasPressed; // Добавляем переменную здесь

    public ModuleManager() {
        modules = new ArrayList<>();
        wasPressed = false; // Инициализируем её
        // Здесь регистрируются все модули
    }

    public void tick() {
        Minecraft mc = Minecraft.getInstance();

        if(mc.currentScreen != null) return;

        long handle = mc.getMainWindow().getHandle();
        boolean isKeyPressed = GLFW.glfwGetKey(handle, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;

        if(isKeyPressed && !wasPressed) {
            mc.displayGuiScreen(new dev.nebula.client.gui.ClickGUI());
        }

        wasPressed = isKeyPressed;

        for(Module module : modules) {
            if(module.isEnabled()) {
                module.tick();
            }
        }
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for(Module m : modules) {
            if(m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
}