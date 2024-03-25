public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;

    public Electronics(int productID, String productName, int productQuantity, double productPrice,
                       String brand, int warrantyPeriod) {
        super(productID, productName, productQuantity, productPrice);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    // Override method to get product information for Electronics
    @Override
    public String getProductInformation() {
        return "Electronics: " + getProductName() + " by " + getBrand() + ", Warranty: " + getWarrantyPeriod() + " months";
    }
}
