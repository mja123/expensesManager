package org.example.utils;

import org.example.model.dto.IDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DTOParser {
    static public Map<String, Map<Object, Object>> getDTOData(IDTO object) {
        // Map with <Attribute: <Type, Value>> form
        Map<String, Map<Object, Object>> values = new HashMap<>();
        // Get dto class getter methods
        List<Method>methods = Arrays.stream(object.getClass().getDeclaredMethods())
                .filter(m -> m.getName().startsWith("get"))
                .toList();
        for (Method method : methods) {
            // Get getter name (attribute)
            String identifier = method.getName().substring(3).toLowerCase();
            Object type = method.getReturnType().getTypeName();
            Object value;
            try {
                // Invoke getter
                value = method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("We couldn't invoke " + method.getName() + " method");
            }
            if (value == null) continue;
            values.put(identifier, Map.of(type, value));
        }
        return values;
    }

    static public String getTableName(IDTO object) {
        String[] splitClassName = object.getClass().getName().split("\\.");
        // Make Java class plural
        return makePlural(splitClassName[splitClassName.length -1].split("(!?DTO)")[0].toLowerCase());
    }

    static private String makePlural(String table) {
        return switch (table.charAt(table.length() -1)) {
            case 's' -> table;
            case 'y' -> table.substring(0, table.length() - 1).concat("ies");
            default -> table.concat("s");
        };
    }
}
