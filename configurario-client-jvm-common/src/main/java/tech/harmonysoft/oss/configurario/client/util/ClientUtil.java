package tech.harmonysoft.oss.configurario.client.util;

import org.jetbrains.annotations.Nullable;
import tech.harmonysoft.oss.configurario.client.ConfigPrefix;

import java.lang.annotation.Annotation;

public class ClientUtil {

    @Nullable
    public static String maybeGetPrefixFromAnnotation(Class<?> klass) {
        for (Annotation annotation : klass.getAnnotations()) {
            if (annotation.annotationType() == ConfigPrefix.class) {
                return ((ConfigPrefix) annotation).value();
            }
        }
        return null;
    }
}
