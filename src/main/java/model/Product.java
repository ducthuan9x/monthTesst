package model;

public class Product {
    private int id;
    private String name;

    private double price;

    CategoryDetail categoryDetail_Id;

    public Product() {
    }

    public Product(int id, String name, double price,CategoryDetail categoryDetail_Id) {
        this.id = id;
        this.name = name;
        this.price=price;
        this.categoryDetail_Id = categoryDetail_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CategoryDetail getCategoryDetail_Id() {
        return categoryDetail_Id;
    }

    public void setCategoryDetail_Id(CategoryDetail categoryDetail_Id) {
        this.categoryDetail_Id = categoryDetail_Id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryDetail_Id=" + categoryDetail_Id +
                '}';
    }
}
