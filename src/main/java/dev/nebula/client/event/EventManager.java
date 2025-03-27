package dev.nebula.client.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {
    private final Map<Class<? extends Event>, List<EventListener>> listeners = new ConcurrentHashMap<>();

    public void register(Object object) {
        for (EventListener listener : findListeners(object)) {
            addEventListener(listener);
        }
    }

    public void unregister(Object object) {
        for (List<EventListener> eventListeners : listeners.values()) {
            eventListeners.removeIf(listener -> listener.getTarget() == object);
        }
    }

    public void call(Event event) {
        List<EventListener> eventListeners = listeners.get(event.getClass());

        if (eventListeners != null) {
            for (EventListener listener : eventListeners) {
                listener.invoke(event);
            }
        }
    }

    private void addEventListener(EventListener listener) {
        listeners.computeIfAbsent(listener.getEventClass(), k -> new CopyOnWriteArrayList<>()).add(listener);
    }

    private List<EventListener> findListeners(Object object) {
        List<EventListener> listeners = new ArrayList<>();

        for (java.lang.reflect.Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class) && method.getParameterCount() == 1) {
                Class<?> parameterType = method.getParameterTypes()[0];
                if (Event.class.isAssignableFrom(parameterType)) {
                    method.setAccessible(true);
                    listeners.add(new EventListener(object, method, (Class<? extends Event>) parameterType));
                }
            }
        }

        return listeners;
    }
}