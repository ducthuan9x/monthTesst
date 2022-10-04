package model;

public class CategoryDetail {
    private int categoryDetailId;
    public String name;
    Category category_id;

    public CategoryDetail() {
    }

    public CategoryDetail(int categoryDetailId, String name, Category category_id) {
        this.categoryDetailId = categoryDetailId;
        this.name = name;
        this.category_id = category_id;
    }

    public int getCategoryDetailId() {
        return categoryDetailId;
    }

    public void setCategoryDetailId(int categoryDetailId) {
        this.categoryDetailId = categoryDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "CategoryDetail{" +
                "id=" + categoryDetailId +
                ", name='" + name + '\'' +
                ", category_id=" + category_id  +
                '}'+ '\n';
    }
}
