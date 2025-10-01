package service;

import model.Category;

public class CategoryService {
    private Category[] categories = new Category[50];
    private static int categoryCount = 0;
    public void addCategory() {
        Category category = new Category();
        categories[categoryCount++] = category;
        category.nhap();

    }
    public Category getCategoryById(String categoryId) {
        for (int i = 0; i < categoryCount; i++) {
            if (categories[i].getCategoryId().equals(categoryId)) {
                return categories[i];
            }
        }
        return null;
    }
}
