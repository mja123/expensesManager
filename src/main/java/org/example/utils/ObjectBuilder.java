package org.example.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder {
    public static Class<?> setValues(ResultSet data, String table) throws SQLException, ClassNotFoundException {
        table = makeSingular(table);
        String className = getClassName(table);
        Class<?> object = Class.forName("org.example.model.dto.".concat(className));
        Map<String, Map<Object, Object>> attributes = getAttributes(data);

        for (Map.Entry<String,Map<Object, Object>> attribute : attributes.entrySet()) {
           String setter = makeSetter(attribute.getKey());
            invokeSetter(object, setter, attribute);
        }
        return object;
    }

    private static void invokeSetter(Class<?> object, String setter,
                                     Map.Entry<String, Map<Object, Object>> attribute) {
        try {
            Map.Entry<Object, Object> row = attribute.getValue().entrySet().stream().findFirst().get();
            System.out.println("Class " + row.getKey());
            Class<?> argumentType = Class.forName(row.getKey().toString()
                            .split("(!?class)")[1].trim());
            Method method = object.getMethod(setter, argumentType);

            method.invoke(object, cast(row.getValue()));

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Map<String, Map<Object, Object>> getAttributes(ResultSet data) throws SQLException, ClassNotFoundException {
        ResultSetMetaData metaData = data.getMetaData();
        Map<String, Map<Object, Object>> attributes = new HashMap<>();
        data.next();
        for (int i = 1; i < metaData.getColumnCount(); i++) {
            Object type = Class.forName(parseSQLTypes(metaData.getColumnTypeName(i)));

            String identifier = metaData.getColumnName(i);
            int columnIndex = data.findColumn(identifier);
            Object value = data.getObject(columnIndex);
            attributes.put(identifier, Map.of(type, value));
        }
        return attributes;
    }
    private static String getClassName(String table) {
        return table.substring(0, 1).toUpperCase()
                .concat(table.substring(1) + "DTO");
    }

    private static String makeSetter(String identifier) {
        return "set"
                .concat(identifier.substring(0, 1).toUpperCase()
                .concat(identifier.substring(1)));
    }

    private static String makeSingular(String table) {
        String suffix = table.substring(table.length() - 3);
        return switch (suffix) {
            case "ies" -> table.substring(0, table.length() - 3).concat("y");
            case ".(!?e)s" -> table;
            default -> table.substring(0, table.length() - 2);
        };
    }

    private static String parseSQLTypes(String type) throws SQLException {
        return switch (type) {
            case "INT" -> "java.lang.Integer";
            case "VARCHAR" -> "java.lang.String";
            default -> throw new SQLException("Type: " + type + " not found");
        };
    }

    public static <T> T cast(Object value) {
        return (T) value;
    }

    // TODO: SOLVED CAST ISSUE TO BE ABLE TO INVOKE SETTERS
}
