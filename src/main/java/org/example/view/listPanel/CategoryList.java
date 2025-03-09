package org.example.view.listPanel;

import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;

public class CategoryList extends AbstractListPanel<CategoryDTO> {

    public CategoryList() {
        // Create new table and set columns
        super(ETable.CATEGORY, new String[]{"ID", "NAME"});
    }

    @Override
    public void addRow(CategoryDTO category) {
        // Populate category table
        tableModel.addRow(new Object[]{
                category.getId(), category.getName()
        });
    }
}
