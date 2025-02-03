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

    public List<ProductModel> getProducts() {;
        String sql = "SELECT * FROM products";
        List<ProductModel> products = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             /* Resultset armazena o resultado da consulta sql,
               então ele faz uma varredura no bd através do rs.next()
              e adiciona na lista de produtos para depois, através do método de listar no ProductService
              retornar todos os produtos do db
             */
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
}
