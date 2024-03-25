public class Clothing extends Product {
    private String productSize;
    private String productColor;


    public Clothing(int productID, String productName, int productQuantity, double productPrice,
                    String productSize, String productColor) {
        super(productID, productName, productQuantity, productPrice);
        this.productSize = productSize;
        this.productColor = productColor;

    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }



    // Implementing the abstract method from the Product class
    @Override
    public String getProductInformation() {
        return "Clothing: " + getProductSize() + " " + getProductColor() + " " + getProductName();
    }

//    @Override
//    public String toString() {
//        return String.format("Product ID : %s\nProduct Name : %s\nNo. Products Available : %d\nProduct Price : %d\nProduct Color : %s\nProduct Size : %d", this.getProductID(), this.getProductName(), this.getProductQuantity(), this.getProductPrice(), this.getProductColor(), this.getProductSize());
//    }
}
