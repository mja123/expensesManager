package org.example.utils.enums;

public enum ETables {
    CATEGORY("categories"),
    EXPENSE("expenses");

    ETables(String name) {
    }

    String getName() {
        return this.name();
    }
}
