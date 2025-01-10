package org.example.view.listPanel;

import org.example.model.dto.CategoryDTO;
import org.example.utils.enums.ETable;

public class CategoryList extends AbstractListPanel<CategoryDTO> {

    public CategoryList() {
        super(ETable.CATEGORY, new String[]{"ID", "NAME"});
    }

    @Override
    public void addRow(CategoryDTO category) {
        tableModel.addRow(new Object[]{
                category.getId(), category.getName()
        });
    }
}
