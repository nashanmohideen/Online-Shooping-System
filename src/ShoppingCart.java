import java.util.ArrayList;

public class ShoppingCart {

    private ArrayList<Product> cart;

    public ShoppingCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Product> cart) {
        this.cart = cart;
    }

    public double calculateTotalCost() {
        double totalCost = 0;
        for (Product product : cart) {
            totalCost += product.getProductPrice();
        }
        return totalCost;
    }

    public boolean applyDiscount() {
        boolean discountApplied = false;

        // Check for the 10% discount for the very first purchase
        if (cart.size() == 1) {
            double totalCost = calculateTotalCost();
            double discountedCost = totalCost * 0.9;
            System.out.println("10% discount applied for the first purchase!");
            System.out.println("Original Total Cost: $" + String.format("%.2f", totalCost));
            System.out.println("Discounted Cost: $" + String.format("%.2f", discountedCost));
            discountApplied = true;
        }

        // Check for the 20% discount for buying at least three products of the same category
        // You need to implement the logic based on your specific requirements
        // This is a placeholder and may not be the exact logic you need
        int electronicsCount = 0;
        int clothingCount = 0;

        for (Product product : cart) {
            if (product instanceof Electronics) {
                electronicsCount++;
            } else if (product instanceof Clothing) {
                clothingCount++;
            }
        }

        if (electronicsCount >= 3 || clothingCount >= 3) {
            double totalCost = calculateTotalCost();
            double discountedCost = totalCost * 0.8;
            System.out.println("20% discount applied for buying at least three products of the same category!");
            System.out.println("Original Total Cost: $" + String.format("%.2f", totalCost));
            System.out.println("Discounted Cost: $" + String.format("%.2f", discountedCost));
            discountApplied = true;
        }

        return discountApplied;
    }
}
