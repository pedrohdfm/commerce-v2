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

    private boolean productExists(int productCode) {
        for (ProductModel product : registeredProducts) {
            if (product.getProductCode() == productCode) {
                return true;
            }
        }
        return false;
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
            if (product.getCostPrice()<0 && product.getSellPrice() <0) {
                System.out.println("Product sell or cost price is negative");
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

    public void listProductsByCategory(String category) {
        if (verifyEmptyList(registeredProducts)) {
            return;
        }
        boolean productExists = false;
        for (ProductModel product : registeredProducts) {
            if (product.getProductCategory().equals(category)) {
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            System.out.println("Product category -> " + category + ", does not exist");
        }
        System.out.println();
        System.out.println("Product list of " + category);
        for(ProductModel product : registeredProducts) {
            if(product.getProductCategory().equals(category)) {
                System.out.println(product);
            }
        }
    }

    public void deleteProduct(int productCode) {
        if (verifyEmptyList(registeredProducts)) {
            return;
        }
        if (productExists(productCode)) {
            registeredProducts.removeIf(product -> product.getProductCode() == productCode);
            productRepository.deleteProductFromDB(productCode);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found with code: " + productCode);
        }
    }

    public void updateProductStock(int productCode, int productStock) {
        if (productStock < 0) {
            throw new IllegalArgumentException("Product stock is negative");
        }
        if (productExists(productCode)) {
            productRepository.updateProductStock(productCode, productStock);
            for (ProductModel product : registeredProducts) {
                if (product.getProductCode() == productCode) {
                    product.setProductStock(productStock);
                    break;
                }
            }
            System.out.println("Product stock updated successfully!");
        } else {
            System.out.println("Product not found with code: " + productCode);
        }
    }

    public void updateProductSellPrice(int productCode, double productSellPrice) {
        if (productSellPrice < 0) {
            throw new IllegalArgumentException("Product sell price is negative");
        }
        if (productExists(productCode)) {
            productRepository.updateProductSellPrice(productCode, productSellPrice);
            for (ProductModel product : registeredProducts) {
                if (product.getProductCode() == productCode) {
                    product.setSellPrice(productSellPrice);
                    break;
                }
            }
            System.out.println("Product sell price updated successfully!");
        } else {
            System.out.println("Product not found with code: " + productCode);
        }
    }

    public void updateProductCostPrice(int productCode, double productCostPrice) {
        if (productCostPrice < 0) {
            throw new IllegalArgumentException("Product cost price is negative");
        }
        if (productExists(productCode)) {
            productRepository.updateProductCostPrice(productCode, productCostPrice);
            for (ProductModel product : registeredProducts) {
                if (product.getProductCode() == productCode) {
                    product.setCostPrice(productCostPrice);
                    break;
                }
            }
            System.out.println("Product cost price updated successfully!");
        } else {
            System.out.println("Product not found with code: " + productCode);
        }
    }
}
