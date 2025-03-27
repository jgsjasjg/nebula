package dev.nebula.module.modules.render;

import dev.nebula.module.Module;
import dev.nebula.module.Category;
import dev.nebula.client.gui.ClickGUI;
import org.lwjgl.glfw.GLFW;

public class ClickGUIModule extends Module {
    private static ClickGUI clickGUI;

    public ClickGUIModule() {
        super("ClickGUI", "Opens the ClickGUI", Category.RENDER);
        this.setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
        clickGUI = new ClickGUI();
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(clickGUI);
        this.toggle(); // Выключаем модуль после открытия GUI
    }
}