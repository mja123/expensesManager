package org.example.view.categories;

import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;
import org.example.view.AbstractListPanel;

public class CategoryList extends AbstractListPanel<CategoryDTO> {

    public CategoryList() {
        super(ETable.CATEGORY, new String[]{"ID", "NAME"});
    }

    @Override
    protected void addRow(CategoryDTO category) {
        tableModel.addRow(new Object[]{
                category.getId(), category.getName()
        });
    }
}
