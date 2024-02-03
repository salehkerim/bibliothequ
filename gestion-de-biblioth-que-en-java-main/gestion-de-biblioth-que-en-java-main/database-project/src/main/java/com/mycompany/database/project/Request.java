package com.mycompany.database.project;

import com.mycompany.database.project.utils.Verification;
import com.mycompany.database.project.utils.DBConnectUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.ImageIcon;

public class Request extends JFrame implements ActionListener {
  JLabel BorrowedBooksLabel, AvailableBooksLabel, lb3, lb4, lb5, lb6;
  JTextField tf1, tf2, tf3, tf4, tf5;
  JButton searchButton, VerrifyButton, SubscriberButton, BorrowedJbutton, BooksJbutton;
  ResultSet rstatement;
  Statement statement;
  JTable tb, tb2, tb3;
  JScrollPane jscrollpane, jscrollpane2, jscroll3;
  JComboBox jcb1, jcb2, jcombox3;

  DBConnectUtil cn = new DBConnectUtil();

  public Request() {
    this.setTitle("Borrows List");
    this.setSize(640, 600);
    this.setLocationRelativeTo(null);
    JPanel pn = new JPanel();
    pn.setLayout(null);
    pn.setBackground(Color.WHITE);
    add(pn);
    ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));

    this.setIconImage(icon.getImage());

    BorrowedBooksLabel = new JLabel("Borrowed Books");
    BorrowedBooksLabel.setBounds(20, 10, 200, 30);
    BorrowedBooksLabel.setFont(new Font("Arial", Font.BOLD, 20));

    pn.add(BorrowedBooksLabel);

    jcb1 = new JComboBox();
    jcb1.addItem("");
    jcb1.setBounds(20, 50, 160, 25);

    String kk5 = "select title from book";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(kk5);
      while (rstatement.next()) {
        jcb1.addItem(rstatement.getString("Title"));

      }
    } catch (SQLException ex) {

    }
    pn.add(jcb1);

    jcb2 = new JComboBox();
    jcb2.addItem("");
    jcb2.setBounds(200, 50, 170, 25);

    String kk6 = "select subName from Subscriber";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(kk6);
      while (rstatement.next()) {
        jcb2.addItem(rstatement.getString("subName"));

      }
    } catch (SQLException ex) {

    }
    pn.add(jcb2);

    searchButton = new JButton("SEARCH");
    searchButton.setBounds(400, 50, 100, 25);
    searchButton.addActionListener(this);
    searchButton.setBackground(Color.decode("#577D86"));
    searchButton.setForeground(Color.white);
    pn.add(searchButton);

    AvailableBooksLabel = new JLabel("Available Books");
    AvailableBooksLabel.setBounds(20, 290, 200, 30);
    AvailableBooksLabel.setFont(new Font("Arial", Font.BOLD, 20));

    pn.add(AvailableBooksLabel);

    jcombox3 = new JComboBox();
    jcombox3.setBounds(20, 330, 160, 25);

    String kk7 = "select title from book";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(kk7);
      while (rstatement.next()) {
        jcombox3.addItem(rstatement.getString("Title"));

      }
    } catch (SQLException ex) {

    }
    pn.add(jcombox3);

    VerrifyButton = new JButton("Verify");
    VerrifyButton.setBounds(210, 330, 100, 25);
    VerrifyButton.addActionListener(this);
    VerrifyButton.setBackground(Color.decode("#577D86"));
    VerrifyButton.setForeground(Color.white);
    pn.add(VerrifyButton);

    SubscriberButton = new JButton("Subscriber");
    SubscriberButton.setBounds(50, 500, 100, 25);
    SubscriberButton.addActionListener(this);
    SubscriberButton.setBackground(Color.decode("#577D86"));
    SubscriberButton.setForeground(Color.white);
    pn.add(SubscriberButton);

    BorrowedJbutton = new JButton("Borrowed");
    BorrowedJbutton.setBounds(180, 500, 100, 25);
    BorrowedJbutton.addActionListener(this);
    BorrowedJbutton.setBackground(Color.decode("#577D86"));
    BorrowedJbutton.setForeground(Color.white);
    pn.add(BorrowedJbutton);

    BooksJbutton = new JButton("Books");
    BooksJbutton.setBounds(300, 500, 100, 25);
    BooksJbutton.addActionListener(this);
    BooksJbutton.setBackground(Color.decode("#577D86"));
    BooksJbutton.setForeground(Color.white);
    pn.add(BooksJbutton);

    DefaultTableModel df = new DefaultTableModel();
    init();
    df.addColumn("Book ID");
    df.addColumn("Book Title");
    df.addColumn("Borrower Name");
    df.addColumn("Borrower ID");
    tb.setModel(df);
    pn.add(jscrollpane);
    String qr = "select * from Subscriber inner join Book on Subscriber.idSub=Book.idsub";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(qr);
      while (rstatement.next()) {
        df.addRow(new Object[] {
          rstatement.getString("idBook"), rstatement.getString("title"), rstatement.getString("subName"), rstatement.getString("idSub")
        });
      }
    } catch (SQLException ex) {

    }

    DefaultTableModel df2 = new DefaultTableModel();
    init2();
    df2.addColumn("Book ID");
    df2.addColumn("Book Title");
    df2.addColumn("Borrower");

    tb2.setModel(df2);

    pn.add(jscrollpane2);

    DefaultTableModel df3 = new DefaultTableModel();
    init3();
    df3.addColumn("ID");
    df3.addColumn("Title");
    tb3.setModel(df3);
    pn.add(jscroll3);
    String qr3 = "select idBook,title from book where available='OUI'";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(qr3);
      while (rstatement.next()) {
        df3.addRow(new Object[] {
          rstatement.getString("idBook"), rstatement.getString("title")
        });
      }
    } catch (SQLException ex) {

    }
  }

  public void init() {
    tb = new JTable();
    jscrollpane = new JScrollPane();
    jscrollpane.setBounds(10, 180, 600, 100);
    jscrollpane.setViewportView(tb);
  }

  public void init2() {
    tb2 = new JTable();
    jscrollpane2 = new JScrollPane();
    jscrollpane2.setBounds(10, 90, 600, 80);
    jscrollpane2.setViewportView(tb2);
  }

  public void init3() {
    tb3 = new JTable();
    jscroll3 = new JScrollPane();
    jscroll3.setBounds(10, 370, 600, 100);
    jscroll3.setViewportView(tb3);
  }

  public static void main(String[] args) {

    Request rq = new Request();
    rq.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == searchButton) {
      String b, c;
      b = jcb1.getSelectedItem().toString();
      c = jcb2.getSelectedItem().toString();
      String qr = "select * from borrow where title='" + b + "' or subName='" + c + "'";

      DefaultTableModel df2 = new DefaultTableModel();
      df2.addColumn("Book ID");
      df2.addColumn("Book Title");
      df2.addColumn("Borrower");
      tb2.setModel(df2);

      try {
        statement = cn.connecion().createStatement();
        rstatement = statement.executeQuery(qr);
        while (rstatement.next()) {
          df2.addRow(new Object[] {
            rstatement.getString("idBook"), rstatement.getString("title"), rstatement.getString("subName")
          });
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Impossible Insertion!", null, JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == VerrifyButton) {
      String a = jcombox3.getSelectedItem().toString();
      Verification vf = new Verification();
      if (vf.verifier(a) == true) {
        JOptionPane.showMessageDialog(this, "AVAILABLE");
      } else
        JOptionPane.showMessageDialog(this, "UNAVAILABLE", null, JOptionPane.ERROR_MESSAGE);

    }

    if (e.getSource() == SubscriberButton) {
      dispose();
      Subscriber ab = new Subscriber();
      ab.setVisible(true);

    }

    if (e.getSource() == BorrowedJbutton) {
      dispose();
      Borrow ep = new Borrow();
      ep.setVisible(true);

    }

    if (e.getSource() == BooksJbutton) {
      dispose();
      Book lv = new Book();
      lv.setVisible(true);
    }
  }
}