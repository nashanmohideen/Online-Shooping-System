import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    public static Scanner scanner=new Scanner(System.in);
    private ArrayList<Product> productList= new ArrayList<>();
    public static void main(String[] args) {
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.loadProducts();
        boolean isRunning = true;
        while (isRunning) {
            manager.displayMainMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1 -> manager.addProduct();
//                        break;
                    case 2 -> manager.removeProduct();
//                        break;
                    case 3 -> manager.viewProduct();
//                        break;
                    case 4 -> manager.saveProduct();

//                        manageCart();
//                        break;
                    case 5 -> manager.customerConsole();
//                        break;
                    case 6 -> isRunning = false;
//                        break;
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public void loadProducts() {
        try {
            FileReader fileReader = new FileReader("Product List.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] productInfo = line.split(",");
                // Assuming the order of values in the file is: ProductID, ProductName, ProductQuantity, ProductPrice, ...

                int productId = Integer.parseInt(productInfo[0]);
                String productName = productInfo[1];
                int productQuantity = Integer.parseInt(productInfo[2]);
                String category= productInfo[3];
                double productPrice = Double.parseDouble(productInfo[4]);

                // Check if it's an Electronics or Clothing based on your file structure
                if (category.equals("Electronic")) {
                    // Electronics
                    String brand = productInfo[5];
                    int warrantyPeriod = Integer.parseInt(productInfo[6]);

                    Product electronics = new Electronics(productId, productName, productQuantity, productPrice, brand, warrantyPeriod);
                    productList.add(electronics);
                } else if (category.equals("Clothing")) {
                    // Clothing
                    String productSize = productInfo[5];
                    String productColor = productInfo[6];

                    Clothing clothing = new Clothing(productId, productName, productQuantity, productPrice, productSize, productColor);
                    productList.add(clothing);
                }
            }
            Collections.sort(productList, (p1, p2) -> Integer.compare(p1.getProductID(), p2.getProductID()));

            bufferedReader.close();
            fileReader.close();
            System.out.println("Products loaded successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayMainMenu() {
        System.out.println("=== Console Shopping Application ===");
        System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. View Products");
            System.out.println("4. Manage Cart");
            System.out.println("5: Open Customer Console");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
        }
    @Override
    public void addProduct() {
        System.out.println("Adding a new product...");

        System.out.println("Choose product type:");
        System.out.println("1. Clothing");
        System.out.println("2. Electronics");
        System.out.print("Enter your choice: ");
        int productTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (productTypeChoice) {
            case 1:
                addClothing();
                break;
            case 2:
                addElectronics();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void addClothing() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Product Quantity: ");
        int productQuantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double productPrice = scanner.nextDouble();

        System.out.print("Enter Product Size: ");
        String productSize = scanner.next();

        System.out.print("Enter Product Color: ");
        String productColor = scanner.next();

        System.out.print("Enter Product Fabric: ");
        String productFabric = scanner.next();

        Clothing newClothing = new Clothing(productId, productName, productQuantity, productPrice, productSize, productColor);
        productList.add(newClothing);

        System.out.println("Clothing added successfully!");
    }
    private void addElectronics() {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Product Quantity: ");
        int productQuantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double productPrice = scanner.nextDouble();

        System.out.print("Enter Brand: ");
        String brand = scanner.next();

        System.out.print("Enter Warranty Period (in months): ");
        int warrantyPeriod = scanner.nextInt();

        Electronics newElectronics = new Electronics(productId, productName, productQuantity, productPrice, brand, warrantyPeriod);
        productList.add(newElectronics);

        System.out.println("Electronics added successfully!");
    }
    @Override
    public void removeProduct() {
        System.out.println("Deleting a product...");
        System.out.print("Enter the Product ID to delete: ");
        int productIDToDelete = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean found = false;
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductID() == productIDToDelete) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }
    @Override
    public void viewProduct() {
        System.out.println("Viewing all products...");
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Product List:");
            for (Product product : productList) {
                System.out.println(product.toString());
                System.out.println("------------------------------------");

            }
        }
    }
    @Override
    public void saveProduct() {
        try {
            FileWriter fileWriter = new FileWriter("Product List.txt");
            for (Product productItem : productList) {
                if (productItem instanceof Electronics) {
                    Electronics electronicItem = (Electronics) productItem;
                    // Assuming you want to save some properties specific to Electronics
                    String item = electronicItem.getProductID() + ","
                            + electronicItem.getProductName() + ","
                            + electronicItem.getProductQuantity() + ",Electronic,"
                            + electronicItem.getProductPrice() + ","
                            + electronicItem.getBrand() + ","
                            + electronicItem.getWarrantyPeriod() + "\n";
                    fileWriter.write(item);
                } else if (productItem instanceof Clothing) {
                    Clothing clothingItem = (Clothing) productItem;
                    // Assuming you want to save some properties specific to Clothing
                    String item = clothingItem.getProductID() + ","
                            + clothingItem.getProductName() + ","
                            + clothingItem.getProductQuantity() + ",Clothing,"
                            + clothingItem.getProductPrice() + ","
                            + clothingItem.getProductSize() + ","
                            + clothingItem.getProductColor() + "\n";
                    fileWriter.write(item);
                }
            }
            fileWriter.close();
            System.out.println("Product information saved successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    @Override
//    public void printProductList() {
//
//    }

    public void customerConsole() {
//        GUIconsole shopInterface= new GUIconsole();
        GUI gui = new GUI();
    }
}
