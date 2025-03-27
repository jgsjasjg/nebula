package dev.nebula.client.gui.component;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.nebula.module.Module;
import dev.nebula.client.Nebula;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class ModuleButton {
    private Module module;
    public int x, y, width, height;

    public ModuleButton(Module module, int x, int y, int width, int height) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        // Фон кнопки
        fill(matrixStack, x, y, x + width, y + height,
                module.isEnabled() ? 0x88000000 : (isHovered(mouseX, mouseY) ? 0x44000000 : 0x22000000));

        // Текст кнопки
        Nebula.INSTANCE.mc.fontRenderer.drawStringWithShadow(
                matrixStack,
                module.getName(),
                x + 5,
                y + 4,
                -1
        );
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(isHovered(mouseX, mouseY) && button == 0) {
            module.toggle();
        }
    }

    private void fill(MatrixStack matrixStack, int x1, int y1, int x2, int y2, int color) {
        float alpha = (float)(color >> 24 & 255) / 255.0F;
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;

        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x1, y2, 0.0D).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(x2, y2, 0.0D).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(x2, y1, 0.0D).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(x1, y1, 0.0D).color(red, green, blue, alpha).endVertex();

        Tessellator.getInstance().draw();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    private boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}