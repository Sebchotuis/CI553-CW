package clients.cashier;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import catalogue.Basket;
import middle.MiddleFactory;
import middle.OrderProcessing;
import middle.StockReadWriter;

/**
 * View of the model.
 * @author M A Smith
 * @version June 2014
 */

public class CashierView implements Observer {
    private static final String CHECK = "Check";
    private static final String BUY = "Buy";
    private static final String BOUGHT = "Bought";

    private final JLabel theAction = new JLabel();
    private final JTextField theInput = new JTextField();
    private final JTextArea theOutput = new JTextArea();
    private final JScrollPane theSP = new JScrollPane();
    private final JButton theBtCheck = new JButton(CHECK);
    private final JButton theBtBuy = new JButton(BUY);
    private final JButton theBtBought = new JButton(BOUGHT);

    private StockReadWriter theStock = null;
    private OrderProcessing theOrder = null;
    private CashierController cont = null;

    // New JLabel for messages
    private final JLabel messageLabel = new JLabel();

    /**
     * Construct the view
     * @param rpc Window in which to construct
     * @param mf Factory to deliver order and stock objects
     * @param x x-coordinate of position of window on screen
     * @param y y-coordinate of position of window on screen
     */
    public CashierView(RootPaneContainer rpc, MiddleFactory mf, int x, int y) {
        try {
            theStock = mf.makeStockReadWriter();
            theOrder = mf.makeOrderProcessing();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        Container cp = rpc.getContentPane();
        cp.setLayout(null);

        // Add the message label
        messageLabel.setBounds(16, 10, 400, 20); // Position it at the top
        messageLabel.setText("Welcome to the Cashier System!");
        cp.add(messageLabel);

        // Add buttons and other components
        theBtCheck.setBounds(16, 25 + 60 * 0, 80, 40);  // Check button
        theBtCheck.addActionListener(e -> cont.doCheck(theInput.getText()));
        cp.add(theBtCheck);

        theBtBuy.setBounds(16, 25 + 60 * 1, 80, 40);    // Buy button
        theBtBuy.addActionListener(e -> cont.doBuy());
        cp.add(theBtBuy);

        theBtBought.setBounds(16, 25 + 60 * 2, 80, 40); // Clear button
        theBtBought.addActionListener(e -> cont.doBought());
        cp.add(theBtBought);

        theAction.setBounds(110, 25, 270, 20);          // Message area
        theAction.setText("");
        cp.add(theAction);

        theInput.setBounds(110, 50, 270, 40);           // Input area
        theInput.setText("");
        cp.add(theInput);
        
        theSP.setBounds(110, 100, 270, 160);            // Scrolling pane
        theOutput.setText("");
        theOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        theOutput.setBackground(Color.DARK_GRAY);
        theOutput.setForeground(Color.WHITE);          // White text for output
        theSP.getViewport().add(theOutput);
        cp.add(theSP);

        cp.setBackground(Color.BLACK);                 // Black background for the container
        theInput.requestFocus();
    }

    /**
     * The controller object, used so that an interaction can be passed to the controller
     * @param c The controller
     */
    public void setController(CashierController c) {
        cont = c;
    }

    /**
     * Update the view
     * @param modelC The observed model
     * @param arg Specific args
     */
    @Override
    public void update(@SuppressWarnings("deprecation") Observable modelC, Object arg) {
        CashierModel model = (CashierModel) modelC;
        String message = (String) arg;

        // Update the message label
        messageLabel.setText(message);

        Basket basket = model.getBasket();
        if (basket == null) {
            theOutput.setText("Customer's order is empty.");
        } else {
            theOutput.setText(basket.getDetails());
        }

        theInput.requestFocus(); // Keep focus on the input field
    }

    /**
     * Applies a dark mode theme to the GUI components.
     */
    private void applyDarkMode() {
        UIManager.put("control", Color.DARK_GRAY);
        UIManager.put("text", Color.WHITE);
        UIManager.put("nimbusLightBackground", Color.BLACK);
        UIManager.put("nimbusSelectionBackground", Color.GRAY);
        UIManager.put("nimbusSelectedText", Color.WHITE);
       
    }
}