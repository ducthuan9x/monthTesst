package service.impl;

import model.Category;
import model.CategoryDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDetailDAO {
    CategoryDAO categoryDAO=new CategoryDAO();
    private String jdbcURL = "jdbc:mysql://localhost:3306/month?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String INSERT_SQL = "INSERT INTO Category" + "  (name) VALUES " +
            " (?);";

    private static final String SELECT_CATEGORYDETAIL_BY_ID = "select * from categoryDetail where categoryDetailId =?";
    private static final String SELECT_ALL = "select * from categoryDetail";

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

    public void insertCategory(Category category) throws SQLException {

    }


    public List<CategoryDetail> selectAllCategoryDetail() {
        List<CategoryDetail> details = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("categoryDetailId");
                String name = rs.getString("name");
                int categoryId=rs.getInt("categoryId");
                Category category=categoryDAO.selectCategory(categoryId);
                details.add(new CategoryDetail(id, name,category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return details;
    }

    public CategoryDetail selectCategoryDetail(int id) {
        CategoryDetail categoryDetail = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORYDETAIL_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int categoryId=rs.getInt("categoryId");
                Category category=categoryDAO.selectCategory(categoryId);
                categoryDetail = new CategoryDetail(id, name,category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return categoryDetail;
    }

    public List<CategoryDetail> findByName(String key) {
        List<CategoryDetail> details = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from categoryDetail where name like ?");) {
            preparedStatement.setString(1, "%" + key + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("categoryDetailId");
                String name = rs.getString("name");
                int categoryId=rs.getInt("categoryId");
                Category category=categoryDAO.selectCategory(categoryId);
                details.add(new CategoryDetail(id, name,category));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return details;
    }

    public List<CategoryDetail>findByCategory(int categoryId){
        List<CategoryDetail> details = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from categoryDetail where categoryId =?");) {
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("categoryDetailId");
                String name = rs.getString("name");
                int categoryId1=rs.getInt("categoryId");
                Category category=categoryDAO.selectCategory(categoryId1);
                details.add(new CategoryDetail(id, name,category));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return details;
    }

}
