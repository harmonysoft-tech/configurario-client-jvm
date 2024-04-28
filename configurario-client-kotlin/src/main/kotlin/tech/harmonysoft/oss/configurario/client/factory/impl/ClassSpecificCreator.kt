package tech.harmonysoft.oss.configurario.client.factory.impl

import tech.harmonysoft.oss.configurario.client.context.Context
import tech.harmonysoft.oss.configurario.client.exception.ConfigurarioException
import tech.harmonysoft.oss.configurario.client.factory.impl.ConfigurarioClientKotlinUtil.parseConstructors
import kotlin.reflect.KClass
import kotlin.reflect.KType

@Suppress("UNCHECKED_CAST")
class ClassSpecificCreator<T : Any>(private val type: KType) {

    private val klass: KClass<T> = type.classifier as? KClass<T> ?: throw ConfigurarioException(
            "Can't instantiate type '$type' - its classifier is not a class"
    )
    private val instantiators: Collection<Instantiator<T>> = parseConstructors(klass).map { Instantiator(it) }

    fun create(prefix: String, creator: KotlinCreator, context: Context): T {
        if (type.classifier == Any::class) {
            return createAny(prefix, context)
        }

        val failedResults = mutableMapOf<Instantiator<T>, String>()

        for (instantiator in instantiators) {
            val candidate = instantiator.mayBeCreate(prefix, creator, context)
            if (candidate.success) {
                return candidate.successValue
            } else {
                failedResults[instantiator] = candidate.failureValue
            }
        }
        throw ConfigurarioException(
            "Failed instantiating a ${klass.qualifiedName ?: klass.simpleName} instance. "
            + "None of ${instantiators.size} constructors match:\n  "
            + failedResults.entries.joinToString(separator = "\n  ") {
                "${it.key} - ${it.value}"
            }
        )
    }

    @Suppress("ALWAYS_NULL")
    private fun createAny(prefix: String, context: Context): T {
        val rawValue = context.getPropertyValue(prefix)
        return if (rawValue == null) {
            if (type.isMarkedNullable) {
                rawValue as T
            } else {
                throw ConfigurarioException(
                        "Failed finding value for property '$prefix'"
                )
            }
        } else {
            val klass = type.classifier as? KClass<T>
            if (klass == null) {
                return rawValue as T
            } else {
                context.convertIfNecessary(rawValue, klass) as T
            }
        }
    }

    override fun toString(): String {
        return "$type creator"
    }
}