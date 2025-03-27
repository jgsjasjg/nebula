package dev.nebula.client.gui.component.frame;

import dev.nebula.client.gui.component.ModuleButton;
import dev.nebula.module.Category;
import dev.nebula.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.List;

public class Frame {
    private Category category;
    private int x, y;
    private int width;
    private int height;
    private boolean extended;
    private boolean dragging;
    private int dragX, dragY;
    private List<ModuleButton> buttons;
    protected static final Minecraft mc = Minecraft.getInstance();

    public Frame(Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 20;
        this.extended = false;
        this.dragging = false;
        this.buttons = new ArrayList<>();
    }

    public void initButtons(List<Module> modules) {
        buttons.clear();
        int buttonY = y + height;
        for (Module module : modules) {
            if (module.getCategory() == category) {
                buttons.add(new ModuleButton(module, x, buttonY, width, 15));
                buttonY += 15;
            }
        }
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        Screen.fill(matrixStack, x, y, x + width, y + height, 0x88000000);
        mc.fontRenderer.drawStringWithShadow(matrixStack, category.getName(), x + 5, y + 6, -1);

        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(matrixStack, mouseX, mouseY);
            }
        }

        if (dragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
            int buttonY = y + height;
            for (ModuleButton button : buttons) {
                button.x = x;
                button.y = buttonY;
                buttonY += 15;
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = mouseX - x;
                dragY = mouseY - y;
            } else if (button == 1) {
                extended = !extended;
            }
        }

        if (extended) {
            for (ModuleButton moduleButton : buttons) {
                moduleButton.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
    }

    private boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}