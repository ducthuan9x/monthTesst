package service.impl;

import model.Category;
import model.CategoryDetail;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    CategoryDetailDAO categoryDetailDAO=new CategoryDetailDAO();
    private String jdbcURL = "jdbc:mysql://localhost:3306/month?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_SQL = "INSERT INTO product" + "  (name, price,categoryDetail) VALUES " +
            " (?,?,?);";

    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id =?";
    private static final String SELECT_ALL = "select * from product";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public void insertProduct(Product product) throws SQLException {

    }


    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int categoryDetailId=rs.getInt("categoryDetailId");
                CategoryDetail detail1=categoryDetailDAO.selectCategoryDetail(categoryDetailId);
                products.add(new Product(id, name,price,detail1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public Product selectProduct(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int categoryDetailId=rs.getInt("categoryDetailId");
                CategoryDetail detail=categoryDetailDAO.selectCategoryDetail(categoryDetailId);
                product = new Product(id, name,price,detail);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    public List<Product> findByName(String key) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");) {
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int categoryDetailId=rs.getInt("categoryDetailId");
                CategoryDetail categoryDetail=categoryDetailDAO.selectCategoryDetail(categoryDetailId);
               products.add(new Product(id, name,price,categoryDetail));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    public List<Product>findByCategoryDetail(int categoryDetailId){
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id, name, price from product where categoryDetailId =?");) {
            preparedStatement.setInt(1, categoryDetailId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price= rs.getDouble("price");
                int categoryDetailId1=rs.getInt("categoryDetailId");
                CategoryDetail categoryDetail=categoryDetailDAO.selectCategoryDetail(categoryDetailId1);
                products.add(new Product(id, name,price,categoryDetail));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }
}
