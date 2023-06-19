package org.example.utils;

import org.example.model.dto.IDTO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DTOParser {
    static public Map<String, Map<Object, Object>> getDTOData(IDTO object) {
        Map<String, Map<Object, Object>> values = new HashMap<>();
        List<Method>methods = Arrays.stream(object.getClass().getDeclaredMethods()).filter(m -> m.getName().startsWith("get")).toList();

        for (Method method : methods) {
            String identifier = method.getName().substring(3);
            Object type = method.getReturnType();
            Object value;
            try {
                value = method.invoke(object, (Object[]) null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("We couldn't invoke " + method.getName() + " method");
            }
            if (value == null) continue;
            values.put(identifier, Map.of(type, value));
        }
        return values;
    }
}
