package org.example.utils.enums;

public enum ETable {
    // All tables
    CATEGORY("categories"),
    EXPENSE("expenses");

    private final String tableName;
    ETable(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return this.tableName;
    }

    // Static method to get the enum constant by value
    public static ETable fromValue(String value) {
        for (ETable table : ETable.values()) {
            if (table.tableName.equals(value)) {
                return table;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + value);
    }
}
