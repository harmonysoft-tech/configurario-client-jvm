package tech.harmonysoft.oss.configurario.client.event;

import org.jetbrains.annotations.NotNull;

/**
 * Generified {@link ConfigChangedEvent} version
 *
 * @param <T>   target config type
 */
public interface GenericConfigChangedEvent<T> {

    @NotNull
    T getPrevious();

    @NotNull
    T getCurrent();
}