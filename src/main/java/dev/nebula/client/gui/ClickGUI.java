package dev.nebula.client.gui;

import dev.nebula.client.gui.component.frame.Frame;
import dev.nebula.module.Category;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {
    private List<Frame> frames;

    public ClickGUI() {
        super(new StringTextComponent("ClickGUI"));
        frames = new ArrayList<>();

        int frameX = 5;
        for (Category category : Category.values()) {
            frames.add(new Frame(category, frameX, 5));
            frameX += 105;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        for (Frame frame : frames) {
            frame.render(matrixStack, mouseX, mouseY);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseClicked((int)mouseX, (int)mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased((int)mouseX, (int)mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}