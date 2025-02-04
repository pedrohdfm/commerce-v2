package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import exception.ProductsException;
import model.ProductModel;
import util.InputHandler;
import service.ProductService;

public class Program {

    public static void main(String[] args) throws ProductsException {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        boolean closedCommerce = false;
        ProductModel product = null;

        UI.welcome();

        while (!closedCommerce) {
            try {
                UI.mainMenu();
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1->{
                        System.out.println("Enter product name: ");
                        String productName = sc.nextLine();
                        System.out.println("Enter the product category: ");
                        String productCategory = sc.nextLine();
                        System.out.println("Enter the product code: ");
                        int productCode = InputHandler.validateProductCode(sc);
                        System.out.println("Enter the product stock: ");
                        int productStock = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter the product sell price: ");
                        double productSellPrice = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Enter the product cost price: ");
                        double productCostPrice = sc.nextDouble();
                        sc.nextLine();

                        product = new ProductModel(productName, productCategory, productCode, productStock, productSellPrice, productCostPrice);
                        productService.registerProduct(product);
                    }
                    case 2-> productService.listProducts();
                    case 3->{}
                    case 4->{}
                    case 5->{}
                    case 6->{
                        System.out.println("Exiting...");
                        closedCommerce = true;
                    }
                    default -> System.out.println("Invalid option");
                }

            } catch (InputMismatchException e) {
                System.out.println("ERROR! Invalid value, please try again.");
                sc.nextLine();
            }
        }
    }
}
