import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIconsole  implements ActionListener {

    public GUIconsole() {
        JFrame shopInterface= new JFrame("Online Shopping Application");
        shopInterface.setLayout(new BorderLayout());
        JPanel newPanel1= new JPanel();
        newPanel1.setLayout(new GridLayout(2,1));
        //newPanel1.setBackground(Color.white);
        newPanel1.setPreferredSize(new Dimension(100, 100));


        JPanel newPanel2= new JPanel();
        newPanel2.setLayout(new GridLayout(2,1));
        //newPanel2.setBackground(Color.white);
        newPanel2.setPreferredSize(new Dimension(100, 100));

        JPanel newPanel3= new JPanel();
        newPanel3.setLayout(new GridLayout(2,1));
        //newPanel3.setBackground(Color.white);
        newPanel3.setPreferredSize(new Dimension(100, 200));

        shopInterface.add(newPanel1, BorderLayout.NORTH);
        shopInterface.add(newPanel2, BorderLayout.CENTER);
        shopInterface.add(newPanel3, BorderLayout.SOUTH);

        shopInterface.setSize(645, 545);
        shopInterface.setVisible(true);
        shopInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopInterface.setLocationRelativeTo(null);


        JPanel filter = new JPanel();
        filter.setLayout(null);
        newPanel1.setLayout(null);
        newPanel1.repaint();
        newPanel1.revalidate();

        newPanel2.setLayout(null);
        newPanel2.repaint();
        newPanel2.revalidate();

        newPanel3.setLayout(null);
        newPanel3.repaint();
        newPanel3.revalidate();

        filter.setBounds(0, 0, 640, 100);

        JLabel selectLabel = new JLabel("Select Category");
        selectLabel.setBounds(220, 50, 96, 16);
        filter.add(selectLabel, BorderLayout.WEST);

        String[] category = {"All", "Electronic", "Clothing"};

        JComboBox<String> selectOption = new JComboBox<>(category);
        selectOption.setBounds(320, 50, 100, 16);
        filter.add(selectOption, BorderLayout.CENTER);
        selectOption.addActionListener(this);

        JButton shoppingCart = new JButton("View Cart");

//        //shoppingCart.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SwingUtilities.invokeLater(() -> {
//                    shopInterface.setVisible(false);
//
//                     cart = new Cart();
//                    // cart.setSize(640, 540);
//                    // cart.setLocationRelativeTo(null);
//
//                    cart.addWindowListener(new WindowAdapter() {
//                        @Override
//                        public void windowClosing(WindowEvent e) {
//                            frame.setVisible(true);
//                        }
//                    });
//                    // cart.setVisible(true);
//                });
//            }
//        });

        shoppingCart.setBounds(520, 10, 100, 24);
        filter.add(shoppingCart, BorderLayout.EAST);
        newPanel1.add(filter);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
