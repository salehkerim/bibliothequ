package com.mycompany.database.project;

import com.mycompany.database.project.utils.DBConnectUtil;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.ImageIcon;

public class Borrow extends JFrame implements ActionListener {

  java.sql.Statement statement;
  ResultSet rstatement;
  DBConnectUtil cn = new DBConnectUtil();
  JComboBox Jcombox, jcombo2;
  JLabel subscriberLabel, BookLabel, borrowBooksLabel, ReturnBooksLabel, SubscriberLabel, BooksLabel, lb7;
  JTextField textfield, textfield5, tf6, tf7;
  JButton borrowbutton, ValidateButton, QueriesButton, BooksButton, SubscriberButton;
  public Borrow() {
    this.setTitle("Borrow Books");
    this.setSize(400, 550);
    this.setLocationRelativeTo(null);
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.WHITE);
    ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));

    this.setIconImage(icon.getImage());
    add(panel);

    borrowBooksLabel = new JLabel("Borrow Books");
    borrowBooksLabel.setBounds(30, 50, 180, 25);
    borrowBooksLabel.setFont(new Font("Arial", Font.BOLD, 18));
    panel.add(borrowBooksLabel);

    subscriberLabel = new JLabel("Subscriber ID");
    subscriberLabel.setBounds(30, 100, 130, 25);
    panel.add(subscriberLabel);
    textfield = new JTextField();
    textfield.setBounds(160, 100, 180, 25);
    panel.add(textfield);

    BookLabel = new JLabel("Book");
    BookLabel.setBounds(30, 150, 130, 25);
    panel.add(BookLabel);

    Jcombox = new JComboBox < > ();
    Jcombox.setBounds(160, 150, 180, 25);
    String query = "SELECT title FROM book";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(query);
      while (rstatement.next()) {
        Jcombox.addItem(rstatement.getString("title"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    panel.add(Jcombox);

    borrowbutton = new JButton("Borrow");
    borrowbutton.setBounds(160, 200, 100, 30);
    borrowbutton.addActionListener(this);
    borrowbutton.setBackground(Color.decode("#577D86"));
    borrowbutton.setForeground(Color.white);
    panel.add(borrowbutton);

    ReturnBooksLabel = new JLabel("Return Books");
    ReturnBooksLabel.setBounds(30, 250, 180, 25);
    ReturnBooksLabel.setFont(new Font("Arial", Font.BOLD, 18));
    panel.add(ReturnBooksLabel);

    SubscriberLabel = new JLabel("Subscriber ID");
    SubscriberLabel.setBounds(30, 300, 130, 25);
    panel.add(SubscriberLabel);
    textfield5 = new JTextField();
    textfield5.setBounds(160, 300, 180, 25);
    panel.add(textfield5);

    BooksLabel = new JLabel("Book");
    BooksLabel.setBounds(30, 350, 130, 25);
    panel.add(BooksLabel);

    jcombo2 = new JComboBox < > ();
    jcombo2.setBounds(160, 350, 180, 25);
    String query2 = "SELECT title FROM book";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(query2);
      while (rstatement.next()) {
        jcombo2.addItem(rstatement.getString("title"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    panel.add(jcombo2);

    ValidateButton = new JButton("Validate");
    ValidateButton.setBounds(160, 400, 100, 30);
    ValidateButton.addActionListener(this);
    ValidateButton.setBackground(Color.decode("#577D86"));
    ValidateButton.setForeground(Color.white);
    panel.add(ValidateButton);

    QueriesButton = new JButton("Queries");
    QueriesButton.setBounds(10, 470, 110, 30);
    QueriesButton.addActionListener(this);
    QueriesButton.setBackground(Color.decode("#577D86"));
    QueriesButton.setForeground(Color.white);
    panel.add(QueriesButton);

    BooksButton = new JButton("Books");
    BooksButton.setBounds(135, 470, 110, 30);
    BooksButton.addActionListener(this);
    BooksButton.setBackground(Color.decode("#577D86"));
    BooksButton.setForeground(Color.white);
    panel.add(BooksButton);

    SubscriberButton = new JButton("Subscribers");
    SubscriberButton.setBounds(260, 470, 110, 30);
    SubscriberButton.addActionListener(this);
    SubscriberButton.setBackground(Color.decode("#577D86"));
    SubscriberButton.setForeground(Color.white);
    panel.add(SubscriberButton);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }

  public static void main(String[] args) {

    Borrow ep = new Borrow();
    ep.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == borrowbutton) {
      String a, b;
      a = textfield.getText();
      b = Jcombox.getSelectedItem().toString();
      String qr = "update Book set available='NON',idSub='" + a + "' where title='" + b + "'";
      try {
        statement = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Add?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          statement.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Success!");
        }

      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failure !", null, JOptionPane.ERROR_MESSAGE);
      }
    }
    if (e.getSource() == ValidateButton) {
      String a, b;
      a = textfield5.getText();
      b = jcombo2.getSelectedItem().toString();
      String qr = "update book set available='OUI',idSub=NULL where idSub='" + a + "' and title='" + b + "'";
      try {
        statement = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Add?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          statement.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Success!");
        }

      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Failure!", null, JOptionPane.ERROR_MESSAGE);
      }

    }

    if (e.getSource() == QueriesButton) {
      dispose();
      Request rq = new Request();
      rq.setVisible(true);

    }

    if (e.getSource() == BooksButton) {
      dispose();
      Book lv = new Book();
      lv.setVisible(true);

    }

    if (e.getSource() == SubscriberButton) {
      dispose();
      Subscriber ab = new Subscriber();
      ab.setVisible(true);

    }

  }

  public void setVisibility(Boolean visibility) {
    this.setVisible(visibility);
  }
}