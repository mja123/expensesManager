package org.example.utils;

import org.example.model.dto.IDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class QueryBuilder {
   public static String create(IDTO object) {
       StringBuilder query = new StringBuilder();
       Map<String, Map<Object, Object>> objectData = DTOParser.getDTOData(object);

       query.append("INSERT INTO ")
               .append(DTOParser.tableName(object))
               .append("(");
       objectData.forEach((key, value) -> query
               .append(key)
               .append(", "));
       query.delete(query.length() - 2, query.length());
       query.append(")")
               .append(" VALUES")
               .append("(");
       query.append("? ".repeat(objectData.size()));
       query.delete(query.length() - 1, query.length());
       query.append(");");
       return query.toString();
   }

   public static String read(IDTO object) {
       StringBuilder query = new StringBuilder();
       Map<String, Map<Object, Object>> objectData = DTOParser.getDTOData(object);

       query.append("SELECT * FROM ")
               .append(DTOParser.tableName(object))
               .append(" WHERE ");
       objectData.forEach((key,value) -> {
           query.append(key);
           value.forEach((k,v) -> {
               query.append(" = ");
               query.append(v);
               query.append(", ");
           });
       });
       query.delete(query.length() - 2, query.length());
       System.out.println("QUERY " + query);
       return query.toString();
   }

    public static void replacePlaceholders(PreparedStatement statement, Map<String, Map<Object, Object>> objectData)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
       int counter = 1;
        for (Map.Entry<String, Map<Object, Object>> data: objectData.entrySet()) {
            setType(statement, data.getValue().entrySet(), counter++);
        }
    }

    public static void  setType(PreparedStatement statement, Set<Map.Entry<Object, Object>> dataSet, int index)
            throws SQLException, NoSuchFieldException, IllegalAccessException {
        Map.Entry<Object, Object> data = dataSet.stream().findFirst().get();

        switch (data.getKey().toString()) {
            case "java.lang.String" -> statement.setString(index, (String) data.getValue());
            case "java.lang.Double" -> statement.setDouble(index, (Double) data.getValue());
            case "java.sql.Date" -> statement.setDate(index, (Date) data.getValue());
            default -> statement.setInt(index, data.getValue().getClass().getDeclaredField("id").getInt(data.getValue()));
        }
    }
}
