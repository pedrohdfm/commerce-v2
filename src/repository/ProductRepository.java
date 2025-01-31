package repository;

import model.ProductModel;
import java.sql.*;
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
            System.out.println("Product inserted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
