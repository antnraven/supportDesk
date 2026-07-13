package com.example.image.utils;

import java.util.Objects;
import java.util.function.Consumer;

public final class BeanUtils {
    private BeanUtils() {
    }

    public static <T> void setIfNotEqual(T targetField, T sourceField, Consumer<T> setter) {
        if (!Objects.equals(targetField, sourceField)) {
            setter.accept(sourceField);
        }
    }
}
