package service;

import java.util.Scanner;

public class InputHandler {

    public static int captureAndValidateProductCode(Scanner sc) {
        boolean validInput = false;
        int productCode = 0;
        while(!validInput) {
            String input = sc.nextLine();
            try {
                productCode = Integer.parseInt(input);
                if (productCode < 0) {
                    throw new  ProductsException("ERROR! Only positive numbers allowed!");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("ERROR! Please type a valid integer!");
            } catch (ProductsException e) {
                System.out.println(e.getMessage());
            }
            if (!validInput) {
                System.out.println("Enter the product code: ");
            }
        }
        return productCode;
    }
}
