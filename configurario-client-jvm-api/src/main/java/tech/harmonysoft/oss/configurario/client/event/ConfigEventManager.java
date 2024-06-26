package tech.harmonysoft.oss.configurario.client.event;

import org.jetbrains.annotations.NotNull;

/**
 * As described in {@link ConfigChangedEvent} documentation, we need to provide an infrastructure for firing
 * config changed events and receiving them. This interface covers it.
 */
public interface ConfigEventManager {

    void fire(@NotNull ConfigChangedEvent event);

    void fire(@NotNull RefreshConfigsEvent event);

    void subscribe(@NotNull ConfigChangedEventAware callback);
}