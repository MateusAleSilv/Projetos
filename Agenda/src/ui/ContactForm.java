package ui;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {

    private JPanel rootPanel;
    private JLabel Nome;
    private JTextField textName;
    private JTextField textPhone;
    private JButton buttonCancel;
    private JButton buttonSave;

    private ContactBusiness mContactBusiness;

    public ContactForm(){

        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();
        setListeners();
    }

    private void setListeners(){

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = textName.getText();
                    String phone = textPhone.getText();
                    mContactBusiness.save(name, phone);
                    new MainForm();
                    dispose();
                } catch (Exception excp){
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainForm();
                dispose();
            }
        });
    }
}
