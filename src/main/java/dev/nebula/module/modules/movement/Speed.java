package dev.nebula.module.modules.movement;

import dev.nebula.module.Module;
import dev.nebula.module.Category;
import dev.nebula.module.Setting;

public class Speed extends Module {
    private Setting<Mode> mode;
    private double speed = 0.0;

    public enum Mode {
        VANILLA("Vanilla"),
        BHOP("BHop"),
        STRAFE("Strafe"),
        ONGROUND("OnGround");

        private final String name;

        Mode(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Speed() {
        super("Speed", "Makes you move faster", Category.MOVEMENT);
        mode = new Setting<>("Mode", Mode.VANILLA);
    }

    public void tick() {
        if (mc.player == null) return;

        switch (mode.getValue()) {
            case VANILLA:
                vanillaSpeed();
                break;
            case BHOP:
                bhopSpeed();
                break;
            case STRAFE:
                strafeSpeed();
                break;
            case ONGROUND:
                onGroundSpeed();
                break;
        }
    }

    private void vanillaSpeed() {
        if (mc.player.moveForward != 0.0f || mc.player.moveStrafing != 0.0f) {
            mc.player.getMotion().x *= 1.8;
            mc.player.getMotion().z *= 1.8;
        }
    }

    private void bhopSpeed() {
        if (mc.player.moveForward != 0.0f || mc.player.moveStrafing != 0.0f) {
            if (mc.player.isOnGround()) {
                mc.player.jump();
                speed = 1.6;
            } else {
                mc.player.getMotion().x *= speed;
                mc.player.getMotion().z *= speed;
                speed *= 0.99; // Постепенное замедление в воздухе
            }
        }
    }

    private void strafeSpeed() {
        if (!mc.player.isOnGround()) return;

        if (mc.player.moveForward != 0.0f || mc.player.moveStrafing != 0.0f) {
            float yaw = mc.player.rotationYaw;
            if (mc.player.moveForward != 0.0f) {
                if (mc.player.moveStrafing > 0.0f) {
                    yaw += (mc.player.moveForward > 0.0f ? -45 : 45);
                } else if (mc.player.moveStrafing < 0.0f) {
                    yaw += (mc.player.moveForward > 0.0f ? 45 : -45);
                }
                if (mc.player.moveForward < 0.0f) {
                    yaw += 180;
                }
            }

            double speed = 0.45;
            mc.player.getMotion().x = -Math.sin(Math.toRadians(yaw)) * speed;
            mc.player.getMotion().z = Math.cos(Math.toRadians(yaw)) * speed;
        }
    }

    private void onGroundSpeed() {
        if (mc.player.isOnGround() && (mc.player.moveForward != 0.0f || mc.player.moveStrafing != 0.0f)) {
            for (int i = 0; i < 3; i++) {
                mc.player.getMotion().x *= 1.35;
                mc.player.getMotion().z *= 1.35;
            }
        }
    }
}