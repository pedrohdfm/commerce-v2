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
                    // REGISTER PRODUCT
                    case 1->{
                        System.out.println("Enter product name: ");
                        String productName = sc.nextLine();
                        System.out.println("Enter the product category: ");
                        String productCategory = sc.nextLine();
                        System.out.println("Enter the product code: ");
                        int productCode = InputHandler.validateProductCode(sc);
                        sc.nextLine();
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
                    // LIST ALL PRODUCTS
                    case 2-> productService.listProducts();
                    case 3->{}
                    // DELETE PRODUCT BY CODE
                    case 4->{
                        productService.listProducts();
                        System.out.println("Please, enter the product code you want to delete: ");
                        sc.nextLine();
                        int productCode = InputHandler.validateProductCode(sc);
                        System.out.println("Are you sure you want to delete this product? Y/N");
                        String ynoption = sc.nextLine();
                        while (!ynoption.equalsIgnoreCase("y") && !ynoption.equalsIgnoreCase("n")) {
                            System.out.println("Please type Y or N");
                            ynoption = sc.nextLine();
                            if (ynoption.equalsIgnoreCase("Y")) {
                                productService.deleteProduct(product, productCode);
                            }
                        }
                    }
                    case 5->{}
                    // EXIT SYSTEM
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
