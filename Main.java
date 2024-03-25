import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class Main implements ShoppingManager {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Product> productList = new ArrayList<>();
    private static ArrayList<Product> shoppingCart = new ArrayList<>();

    public static void main(String[] args) {
        WestminsterShoppingManager manager = new Main();
        boolean isRunning = true;
        while (isRunning) {
            manager.displayMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        manager.addProduct();
                        break;
                    case 2:
                        manager.removeProduct();
                        break;
                    case 3:
                        manager.viewProduct();
                        break;
                    case 4:
                        manageCart();
                        break;
                    case 5:
                        customerConsole();
                        break;
                    case 6:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }

//    private static void displayMainMenu() {
//        System.out.println("=== Console Shopping Application ===");
//        System.out.println("1. Add Product");
//        System.out.println("2. Delete Product");
//        System.out.println("3. View Products");
//        System.out.println("4. Manage Cart");
//        System.out.println("5: Open Customer Console");
//        System.out.println("6. Exit");
//        System.out.print("Enter your choice: ");
//    }

//    @Override
//    public void addProduct() {
//        System.out.println("Adding a new product...");
//
//        System.out.println("Choose product type:");
//        System.out.println("1. Clothing");
//        System.out.println("2. Electronics");
//        System.out.print("Enter your choice: ");
//        int productTypeChoice = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        switch (productTypeChoice) {
//            case 1:
//                addClothing();
//                break;
//            case 2:
//                addElectronics();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }
//
//    private static void addClothing() {
//        System.out.print("Enter Product ID: ");
//        int productId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.print("Enter Product Name: ");
//        String productName = scanner.nextLine();
//
//        System.out.print("Enter Product Quantity: ");
//        int productQuantity = scanner.nextInt();
//
//        System.out.print("Enter Product Price: ");
//        double productPrice = scanner.nextDouble();
//
//        System.out.print("Enter Product Size: ");
//        String productSize = scanner.next();
//
//        System.out.print("Enter Product Color: ");
//        String productColor = scanner.next();
//
//        System.out.print("Enter Product Fabric: ");
//        String productFabric = scanner.next();
//
//        Clothing newClothing = new Clothing(productId, productName, productQuantity, productPrice, productSize, productColor, productFabric);
//        productList.add(newClothing);
//
//        System.out.println("Clothing added successfully!");
//    }
//    private static void addElectronics() {
//        System.out.print("Enter Product ID: ");
//        int productId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.print("Enter Product Name: ");
//        String productName = scanner.nextLine();
//
//        System.out.print("Enter Product Quantity: ");
//        int productQuantity = scanner.nextInt();
//
//        System.out.print("Enter Product Price: ");
//        double productPrice = scanner.nextDouble();
//
//        System.out.print("Enter Brand: ");
//        String brand = scanner.next();
//
//        System.out.print("Enter Warranty Period (in months): ");
//        int warrantyPeriod = scanner.nextInt();
//
//        Electronics newElectronics = new Electronics(productId, productName, productQuantity, productPrice, brand, warrantyPeriod);
//        productList.add(newElectronics);
//
//        System.out.println("Electronics added successfully!");
//    }


//    public void removeProduct() {
//        System.out.println("Deleting a product...");
//        System.out.print("Enter the Product ID to delete: ");
//        int productIDToDelete = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        boolean found = false;
//        Iterator<Product> iterator = productList.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getProductID() == productIDToDelete) {
//                iterator.remove();
//                found = true;
//                break;
//            }
//        }
//
//        if (found) {
//            System.out.println("Product deleted successfully!");
//        } else {
//            System.out.println("Product not found!");
//        }
//    }

//    private static void viewProducts() {
//        System.out.println("Viewing all products...");
//        if (productList.isEmpty()) {
//            System.out.println("No products available.");
//        } else {
//            System.out.println("Product List:");
//            for (Product product : productList) {
//                System.out.println(product.toString());
//                System.out.println("------------------------------------");
//            }
//        }
//    }

    private static void manageCart() {
        System.out.println("Managing the shopping cart...");

        boolean cartMenu = true;
        while (cartMenu) {
            displayCartMenu();
            int cartChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (cartChoice) {
                case 1:
                    viewCart();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    removeFromCart();
                    break;
                case 4:
                    cartMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayCartMenu() {
        System.out.println("=== Cart Menu ===");
        System.out.println("1. View Cart");
        System.out.println("2. Add to Cart");
        System.out.println("3. Remove from Cart");
        System.out.println("4. Exit Cart Menu");
        System.out.print("Enter your choice: ");
    }


    private static void viewCart() {
        System.out.println("=== Shopping Cart ===");
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Products in Cart:");
            for (Product product : shoppingCart) {
                System.out.println(product.toString());
                System.out.println("------------------------------------");
            }
        }
    }

    private static void addToCart() {
        System.out.println("Let's add a product to your cart!");
        if (productList.isEmpty()) {
            System.out.println("Oops! There are no products available to add.");
            return;
        }

        System.out.print("Enter the Product ID to add to your cart: ");
        int productIDToAdd = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product foundProduct = null;
        for (Product product : productList) {
            if (product.getProductID() == productIDToAdd) {
                foundProduct = product;
                break;
            }
        }

        if (foundProduct != null) {
            shoppingCart.add(foundProduct);
            System.out.println("Product added to your cart successfully!");
        } else {
            System.out.println("Product not found in available products.");
        }
    }

    private static void removeFromCart() {
        System.out.println("Let's remove a product from your cart!");
        if (shoppingCart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.print("Enter the Product ID to remove from your cart: ");
        int productIDToRemove = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product foundProduct = null;
        for (Product product : shoppingCart) {
            if (product.getProductID() == productIDToRemove) {
                foundProduct = product;
                break;
            }
        }

        if (foundProduct != null) {
            shoppingCart.remove(foundProduct);
            System.out.println("Product removed from your cart successfully!");
        } else {
            System.out.println("Product not found in your cart.");
        }
    }


    public void saveProduct(){

    }

    @Override
    public void printProductList() {

    }

    public void PrintproductList(){

    }

    public static void customerConsole() {
        GUIconsole shopInterface= new GUIconsole();
    }

}
