package application;

import java.util.Scanner;
import model.ProductModel;
import util.InputHandler;
import service.ProductService;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();


        System.out.println("Enter the name of your product");
        String productName = sc.nextLine();
        System.out.println("Enter the product category");
        String productCategory = sc.nextLine();
        System.out.println("Enter the product code");
        int productCode = InputHandler.captureAndValidateProductCode(sc);
        System.out.println("Enter the product stock");
        int productStock = sc.nextInt();
        System.out.println("Enter the product sell price");
        double productSellPrice = sc.nextDouble();
        System.out.println("Enter the product cost price");
        double productCostPrice = sc.nextDouble();

        ProductModel newProduct = new ProductModel(productName, productCategory, productCode, productStock, productSellPrice, productCostPrice);
        productService.registerProduct(newProduct);

        productService.listProducts();
    }
}
