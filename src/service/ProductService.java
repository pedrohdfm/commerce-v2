package service;

import repository.ProductRepository;
import exception.ProductsException;
import model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductRepository productRepository;
    private List<ProductModel> registeredProducts;

    public ProductService() {
        this.productRepository = new ProductRepository();
        this.registeredProducts = new ArrayList<>();
        this.loadProductsFromDB();
    }

    private void loadProductsFromDB() {
        this.registeredProducts = productRepository.getProducts();
    }

    public boolean verifyCode(int code) {
        for (ProductModel product : registeredProducts) {
            if(product.getProductCode() == code) {
                return false;
            }
        }
        return true;
    }

    private boolean verifyEmptyList(List<ProductModel> registeredProducts) {
        if(registeredProducts.isEmpty()) {
            System.out.println("No products registered yet.");
            return true;
        }
        return false;
    }

    public void registerProduct(ProductModel product) {
        try {
            if (product.getProductName() == null) {
                System.out.println("Product name is empty");
            }
            if (product.getProductStock() < 0) {
                System.out.println("Product stock is negative");
            }
            if (product.getProductCode() < 0) {
                System.out.println("Product code is negative");
            }
            if (verifyCode(product.getProductCode())) {
                productRepository.insertProduct(product);
                registeredProducts.add(product);
                System.out.println("Product registered successfully" + product);
            }else {
                throw new ProductsException("ERROR! Duplicated product code!");
            }
        }catch (ProductsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listProducts() {
        if (verifyEmptyList(registeredProducts)) {
            return;
        }
        System.out.println();
        System.out.println("Product list");
        for (ProductModel product : registeredProducts) {
            System.out.println(product);
        }
    }
}
