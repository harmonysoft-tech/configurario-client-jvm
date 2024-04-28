package tech.harmonysoft.oss.configurario.client.factory.impl

import tech.harmonysoft.oss.configurario.client.event.ConfigChangedEvent
import tech.harmonysoft.oss.configurario.client.event.ConfigChangedEventAware
import tech.harmonysoft.oss.configurario.client.event.ConfigEventManager
import tech.harmonysoft.oss.configurario.client.event.RefreshConfigsEvent
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CopyOnWriteArraySet

class TestConfigEventManager : ConfigEventManager {

    val firedEvents = CopyOnWriteArraySet<Any>()
    private val callbacks = CopyOnWriteArrayList<ConfigChangedEventAware>()

    override fun fire(event: ConfigChangedEvent) {
        firedEvents += event
        for (callback in callbacks) {
            callback.onConfigChanged(event)
        }
    }

    override fun fire(event: RefreshConfigsEvent) {
        firedEvents += event
        for (callback in callbacks) {
            callback.onRefreshEvent()
        }
    }

    override fun subscribe(callback: ConfigChangedEventAware) {
        callbacks += callback
    }

    fun onTestEnd() {
        callbacks.clear()
        firedEvents.clear()
    }
}