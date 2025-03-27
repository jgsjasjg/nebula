package dev.nebula.module;

import dev.nebula.module.modules.movement.Speed;
import dev.nebula.module.Module;
import net.minecraft.client.Minecraft;
import dev.nebula.module.modules.render.ClickGUIModule;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private List<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new ClickGUIModule());
        modules.add(new Speed());
    }

    public List<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        return modules.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<Module> getModulesByCategory(Category category) {
        List<Module> categoryModules = new ArrayList<>();
        for (Module module : modules) {
            if (module.getCategory() == category) {
                categoryModules.add(module);
            }
        }
        return categoryModules;
    }

    public void tick() {
        for (Module module : modules) {
            if (GLFW.glfwGetKey(mc.getMainWindow().getHandle(), module.getKey()) == GLFW.GLFW_PRESS) {
                module.toggle();
            }
        }
    }
}