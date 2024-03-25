import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    private JComboBox<String> categoryComboBox;
    private JTable productTable;
    private JTextArea detailsTextArea;
    private JButton addToCartButton;
    private JButton viewCartButton;

    private ShoppingCart shoppingCart;
    private ArrayList<Product> productList;

    public GUI() {
        initializeData();
        initializeGUI();
    }

    private void initializeData() {
        productList = new ArrayList<>();
        productList.add(new Electronics(1, "Laptop", 800, 10, "HP", 1));
        productList.add(new Clothing(2, "T-shirt", 20, 15, "XL", "Red"));

        shoppingCart = new ShoppingCart(new ArrayList<>());
    }

    private void initializeGUI() {
        setTitle("Online Shopping System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        categoryComboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        categoryComboBox.addActionListener(this);

        productTable = new JTable();
        // Initialize the detailsTextArea before calling updateTableData
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(this);

        viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(this);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Choose Category:"));
        topPanel.add(categoryComboBox);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);
        centerPanel.add(detailsTextArea, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(addToCartButton);
        bottomPanel.add(viewCartButton);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Call updateTableData after initializing detailsTextArea
        updateTableData("All");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTableData(String selectedCategory) {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Product Name", "Price", "Availability"});

        for (Product product : productList) {
            if (selectedCategory.equals("All") ||
                    (product instanceof Electronics && selectedCategory.equals("Electronics")) ||
                    (product instanceof Clothing && selectedCategory.equals("Clothing"))) {
                Object[] row = {product.getProductID(), product.getProductName(), product.getProductPrice(), product.getProductQuantity()};
                model.addRow(row);
            }
        }

        productTable.setModel(model);
        productTable.getColumnModel().getColumn(0).setPreferredWidth(50);

        // Clear details area when updating the table
        detailsTextArea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == categoryComboBox) {
            updateTableData(categoryComboBox.getSelectedItem().toString());
        } else if (e.getSource() == addToCartButton) {
            addToCart();
        } else if (e.getSource() == viewCartButton) {
            viewShoppingCart();
        }
    }

    private void addToCart() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow != -1) {
            Product selectedProduct = getProductFromTable(selectedRow);
            if (selectedProduct != null) {
                shoppingCart.getCart().add(selectedProduct);
                updateShoppingCart();
            }
        }
    }

    private void viewShoppingCart() {
        StringBuilder cartDetails = new StringBuilder("Shopping Cart:\n");

        if (!shoppingCart.getCart().isEmpty()) {
            for (Product product : shoppingCart.getCart()) {
                cartDetails.append(product.getProductInformation()).append("\n");
            }

            double totalCost = shoppingCart.calculateTotalCost();
            boolean discountApplied = shoppingCart.applyDiscount();
            String discountMessage = discountApplied ? "Discount Applied!" : "";

            cartDetails.append("\nTotal Cost: $").append(String.format("%.2f", totalCost)).append("\n").append(discountMessage);

            JOptionPane.showMessageDialog(this, cartDetails.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Shopping Cart is empty!");
        }
    }

    private Product getProductFromTable(int rowIndex) {
        int productId = Integer.parseInt(productTable.getValueAt(rowIndex, 0).toString());
        for (Product product : productList) {
            if (product.getProductID() == productId) {
                return product;
            }
        }
        return null;
    }

    private void updateShoppingCart() {
        StringBuilder cartDetails = new StringBuilder("Product added to the shopping cart!\n");

        // Display the current contents of the shopping cart
        if (!shoppingCart.getCart().isEmpty()) {
            cartDetails.append("Shopping Cart:\n");
            for (Product product : shoppingCart.getCart()) {
                cartDetails.append(product.getProductInformation()).append("\n");
            }

            double totalCost = shoppingCart.calculateTotalCost();
            boolean discountApplied = shoppingCart.applyDiscount();
            String discountMessage = discountApplied ? "Discount Applied!" : "";

            cartDetails.append("\nTotal Cost: $").append(String.format("%.2f", totalCost)).append("\n").append(discountMessage);

            JOptionPane.showMessageDialog(this, cartDetails.toString());
        } else {
            JOptionPane.showMessageDialog(this, "Shopping Cart is empty!");
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}
