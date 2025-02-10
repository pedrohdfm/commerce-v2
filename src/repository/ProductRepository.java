package repository;

import model.ProductModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static repository.ConnectionDB.getConnection;

public class ProductRepository {


    public void insertProduct(ProductModel product) {
        String sql = "INSERT INTO products (productName, productCategory, productCode, productStock, productSellPrice, productCostPrice) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getProductCategory());
            pstmt.setInt(3, product.getProductCode());
            pstmt.setInt(4, product.getProductStock());
            pstmt.setDouble(5, product.getSellPrice());
            pstmt.setDouble(6, product.getCostPrice());

            pstmt.executeUpdate();
            System.out.println("Product successfully inserted into database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductModel> getProducts() {
        String sql = "SELECT * FROM products";
        List<ProductModel> products = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getString("productName"),
                        rs.getString("productCategory"),
                        rs.getInt("productCode"),
                        rs.getInt("productStock"),
                        rs.getDouble("productSellPrice"),
                        rs.getDouble("productCostPrice")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void deleteProductFromDB(int productCode) {
        String sql = "DELETE FROM products WHERE productCode = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productCode);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("No product found with code: " + productCode);
            }

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR! Cannot delete product from database." + e.getMessage());
        }
    }


    public void updateProductStock(int productCode, int productStock) {
        String sql = "UPDATE products SET productStock = ? WHERE productCode = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productStock);
            pstmt.setInt(2, productCode);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product stock updated successfully in the database!");
            } else {
                System.out.println("No product found with code: " + productCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR! Cannot update product stock in the database: " + e.getMessage());
        }
    }

    public void updateProductSellPrice(int productCode, double productSellPrice) {
        String sql = "UPDATE products SET productSellPrice = ? WHERE productCode = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, productSellPrice);
            pstmt.setInt(2, productCode);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product sell price updated successfully in the database!");
            } else {
                System.out.println("No product found with code: " + productCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR! Cannot update product sell price in the database: " + e.getMessage());
        }
    }

    public void updateProductCostPrice(int productCode, double productCostPrice) {
        String sql = "UPDATE products SET productCostPrice = ? WHERE productCode = ?";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, productCostPrice);
            pstmt.setInt(2, productCode);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product cost price updated successfully in the database!");
            } else {
                System.out.println("No product found with code: " + productCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR! Cannot update product cost price in the database: " + e.getMessage());
        }
    }
}
