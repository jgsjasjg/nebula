package dev.nebula.client.event.events;

import dev.nebula.client.event.Event;

public class UpdateEvent extends Event {
    private float yaw;
    private float pitch;
    private boolean onGround;
    private double x, y, z;

    public UpdateEvent(float yaw, float pitch, boolean onGround, double x, double y, double z) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}