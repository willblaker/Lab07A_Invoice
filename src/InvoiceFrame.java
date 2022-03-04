import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InvoiceFrame extends JFrame {
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel lineItemsPnl;
    JPanel totalDuePnl;

    JLabel titleTxt;

    JTextArea lineItemsTxt;
    JTextArea totalTxt;

    JButton totalBtn;

    public InvoiceFrame(){
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createLineItemsPnl();
        mainPnl.add(lineItemsPnl, BorderLayout.CENTER);

        createTotalDuePnl();
        mainPnl.add(totalDuePnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(400,500);
        setVisible(true);
    }

    public void createTitlePnl(){
        titlePnl = new JPanel();
        titleTxt = new JLabel();

        titleTxt.setText("INVOICE");
        titleTxt.setFont(new Font("Serif", Font.PLAIN, 36));
        titleTxt.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleTxt);
    }

    public void createLineItemsPnl(){
        lineItemsPnl = new JPanel();
        lineItemsTxt = new JTextArea("", 3,21);

        lineItemsPnl.add(lineItemsTxt);

        totalTxt = new JTextArea("",1,21);
        lineItemsPnl.add(totalTxt);
    }

    public void createTotalDuePnl(){
        totalDuePnl = new JPanel();
        totalBtn = new JButton("Add Item");

        totalBtn.addActionListener((ActionEvent ae)->
            {
                totalTxt.selectAll();
                totalTxt.replaceSelection("");

                String Product = "";
                String p = null;

                do{
                     p = (String)JOptionPane.showInputDialog(
                            mainPnl,
                            "Input Product Name",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            ""
                    );
                    if(p != null && p.length() > 0){
                        Product = p;
                        p = "next";
                    }
                    else{
                        p = null;
                        JOptionPane.showMessageDialog(null, "Invalid Product!, Please try again.");
                    }
                }while(p == null);

                int Quantity = 0;
                String q = null;
                do{
                    q = (String) JOptionPane.showInputDialog(
                            mainPnl,
                            "Input Product Quantity",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            ""
                    );
                    if(q != null && q.length() > 0){
                        Quantity = Integer.parseInt(q);
                        q = "next";
                    }
                    else{
                        q = null;
                        JOptionPane.showMessageDialog(null, "Invalid Quantity!, Please try again.");
                    }
                }while(q == null);

                double unitPrice = 0;
                String u = null;
                do{
                    u = (String) JOptionPane.showInputDialog(
                            mainPnl,
                            "Input Unit Price",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            ""
                    );
                    if(u != null && u.length() > 0){
                        unitPrice = Double.parseDouble(u);
                        u = "next";
                    }
                    else{
                        u = null;
                        JOptionPane.showMessageDialog(null, "Invalid Price!, Please try again.");
                    }
                }while(u == null);

                Product product = new Product(Product, unitPrice);
                lineItemsTxt.append(LineItems.listFormat(product, Quantity) + "\n");
                totalTxt.append(LineItems.amountDue + "");
            }
        );
        totalDuePnl.add(totalBtn);
    }
}
