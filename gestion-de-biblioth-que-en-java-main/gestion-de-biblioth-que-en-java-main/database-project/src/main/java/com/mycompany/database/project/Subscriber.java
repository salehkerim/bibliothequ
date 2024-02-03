package com.mycompany.database.project;

import com.mycompany.database.project.utils.DBConnectUtil;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
import javax.swing.table.DefaultTableCellRenderer;

public class Subscriber extends JFrame implements ActionListener {
  JLabel IdentifierLabel, FullNameLabel, lb3, lb4, lb5, AddSubscribersLabel;
  JTextField tf1, tf2, tf3, tf4, tf5;
  JButton InsertJbutton, DeleteJbutton, RequestsJbutton, ResearchJbutton, RefreshJbutton, ModificationJbutton, BooksJbutton, BorrowJbutton;
  ResultSet rstatement;
  Statement statement;
  DBConnectUtil cn = new DBConnectUtil();
  JTable jt;
  JScrollPane jscroll;

  public Subscriber() {
    this.setTitle("Add Subscribers");
    this.setSize(700, 400);
    this.setLocationRelativeTo(null);
    JPanel pn = new JPanel();
    pn.setLayout(null);
    pn.setBackground(Color.white);
    add(pn);
    ImageIcon icon = new ImageIcon(getClass().getResource("icon.png"));

    this.setIconImage(icon.getImage());

    AddSubscribersLabel = new JLabel("Add Subscribers");
    AddSubscribersLabel.setBounds(20, 10, 300, 30);
    AddSubscribersLabel.setFont(new Font("Arial", Font.BOLD, 16));
    pn.add(AddSubscribersLabel);

    IdentifierLabel = new JLabel("Identifier");
    IdentifierLabel.setBounds(20, 50, 100, 30);
    pn.add(IdentifierLabel);
    tf1 = new JTextField();
    tf1.setBounds(100, 50, 100, 30);
    pn.add(tf1);

    FullNameLabel = new JLabel("Full Name");
    FullNameLabel.setBounds(20, 90, 170, 30);
    pn.add(FullNameLabel);

    tf2 = new JTextField();
    tf2.setBounds(100, 90, 180, 30);
    pn.add(tf2);

    pn.add(tf2);

    InsertJbutton = new JButton("INSERT");
    InsertJbutton.setBounds(20, 130, 120, 30);
    InsertJbutton.addActionListener(this);
    InsertJbutton.setBackground(Color.decode("#577D86"));
    InsertJbutton.setForeground(Color.white);
    pn.add(InsertJbutton);

    DeleteJbutton = new JButton("DELETE");
    DeleteJbutton.setBounds(150, 130, 130, 30);
    DeleteJbutton.addActionListener(this);
    DeleteJbutton.setBackground(Color.decode("#577D86"));
    DeleteJbutton.setForeground(Color.white);
    pn.add(DeleteJbutton);

    ResearchJbutton = new JButton("🔍");
    ResearchJbutton.setBounds(200, 50, 80, 30);
    ResearchJbutton.addActionListener(this);
    ResearchJbutton.setBackground(Color.decode("#577D86"));
    ResearchJbutton.setForeground(Color.white);
    pn.add(ResearchJbutton);

    RequestsJbutton = new JButton("REQUESTS");
    RequestsJbutton.setBounds(20, 240, 120, 30);
    RequestsJbutton.addActionListener(this);
    RequestsJbutton.setBackground(Color.decode("#577D86"));
    RequestsJbutton.setForeground(Color.white);
    pn.add(RequestsJbutton);

    RefreshJbutton = new JButton("REFRESH");
    RefreshJbutton.setBounds(20, 280, 120, 30);
    RefreshJbutton.addActionListener(this);
    RefreshJbutton.setBackground(Color.decode("#577D86"));
    RefreshJbutton.setForeground(Color.white);
    pn.add(RefreshJbutton);

    ModificationJbutton = new JButton("MODIFICATION");
    ModificationJbutton.setBounds(20, 205, 260, 30);
    ModificationJbutton.addActionListener(this);
    ModificationJbutton.setBackground(Color.decode("#577D86"));
    ModificationJbutton.setForeground(Color.white);
    pn.add(ModificationJbutton);

    BooksJbutton = new JButton("BOOKS");
    BooksJbutton.setBounds(150, 280, 130, 30);
    BooksJbutton.addActionListener(this);
    BooksJbutton.setBackground(Color.decode("#577D86"));
    BooksJbutton.setForeground(Color.white);
    pn.add(BooksJbutton);

    BorrowJbutton = new JButton("BORROW");
    BorrowJbutton.setBounds(150, 240, 130, 30);
    BorrowJbutton.addActionListener(this);
    BorrowJbutton.setBackground(Color.decode("#577D86"));
    BorrowJbutton.setForeground(Color.white);
    pn.add(BorrowJbutton);

    DefaultTableModel df = new DefaultTableModel();
    init();
    df.addColumn("IDENTIFIER");
    df.addColumn("FULLNAME");
    jt.setModel(df);

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    cellRenderer.setForeground(Color.decode("#569DAA"));

    for (int i = 0; i < jt.getColumnCount(); i++) {
      jt.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
    }

    pn.add(jscroll);

    String rq = "select * from subscriber";
    try {
      statement = cn.connecion().createStatement();
      rstatement = statement.executeQuery(rq);
      while (rstatement.next()) {
        df.addRow(new Object[] {
          rstatement.getString("idsub"), rstatement.getString("subName")
        });

      }
    } catch (SQLException ex) {

    }
  }
  public void init() {
    jt = new JTable();
    jscroll = new JScrollPane();
    jscroll.setViewportView(jt);
    jscroll.setBounds(320, 10, 350, 300);
  }

  public static void main(String[] args) {

    Subscriber ab = new Subscriber();
    ab.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == InsertJbutton) {
      String a, b;
      a = tf1.getText();
      b = tf2.getText();
      String qr = "insert into subscriber values('" + a + "','" + b + "')";
      try {
        statement = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Insert?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          statement.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Success!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Insertion Error!", null, JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == DeleteJbutton) {
      String a;
      a = tf1.getText();
      String qr = "delete from subscriber where idsub='" + a + "'";
      try {
        statement = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Delete?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          statement.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Deletion Success!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Deletion Impossible!", null, JOptionPane.ERROR_MESSAGE);
      }
    }

    if (e.getSource() == ResearchJbutton) {
      String a;
      a = tf1.getText();
      String qr = "select * from subscriber where idsub='" + a + "'";
      try {
        statement = cn.connecion().createStatement();
        rstatement = statement.executeQuery(qr);
        if (rstatement.next()) {
          tf2.setText(rstatement.getString("subName"));
        } else
          JOptionPane.showMessageDialog(this, "Not found!", null, JOptionPane.ERROR_MESSAGE);
      } catch (SQLException ex) {

      }

    }
    if (e.getSource() == RequestsJbutton) {
      dispose();
      Request rq = new Request();
      rq.setVisible(true);

    }
    if (e.getSource() == RefreshJbutton) {
      dispose();
      Subscriber ab = new Subscriber();
      ab.setVisible(true);

    }

    if (e.getSource() == ModificationJbutton) {
      String a, b;
      a = tf1.getText();
      b = tf2.getText();
      String qr = "update subscriber set subName='" + b + "' where idSub='" + a + "'";
      try {
        statement = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Modify?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          statement.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Successful modification!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Modification Error!", null, JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == BooksJbutton) {
      dispose();
      Book lv = new Book();
      lv.setVisible(true);
    }

    if (e.getSource() == BorrowJbutton) {
      dispose();
      Borrow ep = new Borrow();
      ep.setVisible(true);
    }
  }

}