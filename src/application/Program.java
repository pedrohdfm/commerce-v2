package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.ProductModel;
import util.InputHandler;
import service.ProductService;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        boolean closedCommerce = false;
        ProductModel product;

        UI.welcome();

        while (!closedCommerce) {
            try {
                UI.mainMenu();
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    // REGISTER PRODUCT
                    case 1 -> {
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
                    case 2 -> productService.listProducts();
                    // LIST PRODUCT BY CATEGORY
                    case 3 -> {
                        System.out.println("Enter product category: ");
                        String category = sc.nextLine();
                        productService.listProductsByCategory(category);
                    }
                    // DELETE PRODUCT BY CODE
                    case 4 -> {
                        productService.listProducts();
                        System.out.println("Please, enter the product code you want to delete: ");
                        int productCode = InputHandler.validateProductCode(sc);
                        System.out.println("Are you sure you want to delete this product? Y/N (Yes or No)");
                        String ynoption = sc.nextLine();
                        while (!ynoption.equalsIgnoreCase("y") && !ynoption.equalsIgnoreCase("n")) {
                            System.out.println("Please, type Y or N");
                            ynoption = sc.nextLine();
                        }
                        if (ynoption.equalsIgnoreCase("y")) {
                            productService.deleteProduct(productCode);
                        } else {
                            System.out.println("Deletion cancelled.");
                        }
                    }
                    // UPDATE PRODUCT
                    case 5 -> {
                        productService.listProducts();
                        System.out.println("First, enter the product code you want to update: ");
                        int productCode = InputHandler.validateProductCode(sc);
                        boolean backToMenu = false;
                        while (!backToMenu) {
                            UI.updateProductMenu();
                            int updateOption = sc.nextInt();
                            sc.nextLine();

                            switch (updateOption) {
                                case 1 -> {
                                    System.out.println("Enter the new product stock: ");
                                    int productStock = sc.nextInt();
                                    sc.nextLine();
                                    productService.updateProductStock(productCode, productStock);
                                }
                                case 2 -> {
                                    System.out.println("Enter the new sell price: ");
                                    double productSellPrice = sc.nextDouble();
                                    sc.nextLine();
                                    productService.updateProductSellPrice(productCode, productSellPrice);
                                }
                                case 3 -> {
                                    System.out.println("Enter the new cost price: ");
                                    double productCostPrice = sc.nextDouble();
                                    sc.nextLine();
                                    productService.updateProductCostPrice(productCode, productCostPrice);
                                }
                                case 4 -> {
                                    backToMenu = true;
                                    System.out.println("Returning to the main menu...");
                                }
                                default -> System.out.println("Invalid option, please try again.");
                            }
                        }

                    }
                    // EXIT SYSTEM
                    case 6 -> {
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
        sc.close();
    }
}