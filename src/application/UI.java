package application;

public class UI {

    public static void welcome() {
        System.out.println("==============================================");
        System.out.println(" __        __   _                            ");
        System.out.println(" \\ \\      / /__| | ___ ___  _ __ ___   ___   ");
        System.out.println("  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\  ");
        System.out.println("   \\ V  V /  __/ | (_| (_) | | | | | |  __/  ");
        System.out.println("    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|  ");
        System.out.println("");
        System.out.println("E-commerce v2 project, by pedrohdfm");
        System.out.println("==============================================");
    }

    public static void mainMenu() {
            System.out.println("1. Register product");
            System.out.println("2. List all products");
            System.out.println("3. List products by category");
            System.out.println("4. Delete product");
            System.out.println("5. Update product");
            System.out.println("6. Exit");
            System.out.println("Please enter your choice: ");
    }
}
