package tech.harmonysoft.oss.configurario.client.event.impl;

import org.jetbrains.annotations.NotNull;
import tech.harmonysoft.oss.configurario.client.event.ConfigChangedEvent;
import tech.harmonysoft.oss.configurario.client.event.ConfigChangedEventAware;
import tech.harmonysoft.oss.configurario.client.event.ConfigEventManager;
import tech.harmonysoft.oss.configurario.client.event.RefreshConfigsEvent;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultConfigEventManager implements ConfigEventManager {

    private final Set<ConfigChangedEventAware> callbacks = Collections.newSetFromMap(new ConcurrentHashMap<>());

    @Override
    public void fire(@NotNull ConfigChangedEvent event) {
        for (ConfigChangedEventAware callback : callbacks) {
            callback.onConfigChanged(event);
        }
    }

    @Override
    public void fire(@NotNull RefreshConfigsEvent event) {
        for (ConfigChangedEventAware callback : callbacks) {
            callback.onRefreshEvent();
        }
    }

    @Override
    public void subscribe(@NotNull ConfigChangedEventAware callback) {
        callbacks.add(callback);
    }
}
