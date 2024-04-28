package tech.harmonysoft.oss.configurario.client.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tech.harmonysoft.oss.configurario.client.event.ConfigEventManager
import tech.harmonysoft.oss.configurario.client.event.impl.DefaultConfigEventManager

@Configuration
class ConfigurarioAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    fun defaultConfigEventManager(): ConfigEventManager {
        return DefaultConfigEventManager()
    }
}