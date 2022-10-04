package main;

import service.impl.CategoryDAO;
import service.impl.CategoryDetailDAO;
import service.impl.ProductDAO;

public class Test {
    public static void main(String[] args) {
        CategoryDAO categoryDAO=new CategoryDAO();
//        System.out.println(categoryDAO.selectAllCategory());
//        System.out.println(categoryDAO.findByName("điện"));

        CategoryDetailDAO categoryDetailDAO=new CategoryDetailDAO();
//        System.out.println(categoryDetailDAO.selectAllCategoryDetail());
//        System.out.println(categoryDetailDAO.selectCategoryDetail(1));
//        System.out.println(categoryDetailDAO.findByName("TH"));
//        System.out.println(categoryDetailDAO.findByCategory(1));

        ProductDAO productDAO=new ProductDAO();
//        System.out.println(productDAO.selectAllProduct());
//        System.out.println(productDAO.findByCategoryDetail(1));
        System.out.println(productDAO.findByName("vi"));
    }
}
