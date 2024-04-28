package tech.harmonysoft.oss.configurario.client.exception

class ConfigurarioException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)