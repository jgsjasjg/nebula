package dev.nebula.client.event;

import java.lang.reflect.Method;

public class EventListener {
    private final Object target;
    private final Method method;
    private final Class<? extends Event> eventClass;

    public EventListener(Object target, Method method, Class<? extends Event> eventClass) {
        this.target = target;
        this.method = method;
        this.eventClass = eventClass;
    }

    public void invoke(Event event) {
        try {
            method.invoke(target, event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getTarget() {
        return target;
    }

    public Class<? extends Event> getEventClass() {
        return eventClass;
    }
}