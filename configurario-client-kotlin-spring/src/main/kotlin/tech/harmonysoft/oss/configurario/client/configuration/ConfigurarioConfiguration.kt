package tech.harmonysoft.oss.configurario.client.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tech.harmonysoft.oss.configurario.client.context.ContextProvider
import tech.harmonysoft.oss.configurario.client.event.ConfigEventManager
import tech.harmonysoft.oss.configurario.client.factory.ConfigProviderFactory
import tech.harmonysoft.oss.configurario.client.factory.impl.KotlinConfigProviderFactory

@Configuration
class ConfigurarioConfiguration {

    @Bean
    fun configProviderFactory(
        contextProvider: ContextProvider,
        eventManger: ConfigEventManager
    ): ConfigProviderFactory {
        return KotlinConfigProviderFactory(contextProvider, eventManger)
    }
}