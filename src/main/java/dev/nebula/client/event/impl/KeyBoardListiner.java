package dev.nebula.client.event.impl;

import dev.nebula.client.Nebula;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class KeyBoardListiner {
    public static void handleKeyboard() {
        if (GLFW.glfwGetKey(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS) {
            Minecraft.getInstance().displayGuiScreen(new dev.nebula.client.gui.ClickGUI());
        }
    }
}