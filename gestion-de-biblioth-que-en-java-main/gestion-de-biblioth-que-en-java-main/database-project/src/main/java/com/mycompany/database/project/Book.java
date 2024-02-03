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
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class Book extends JFrame implements ActionListener {
  ImageIcon icon;
  JLabel label1, label2, label3, label4, label5, labeltitle, footerLabel;
  JTextField tf1, tf2, tf3, tf4, tf5;
  JButton insertB, deleteB, reqB, searchB, modifB, refreshB, subB, borrowB;
  ResultSet rst;
  Statement st;
  DBConnectUtil cn = new DBConnectUtil();
  JTable jt;
  JScrollPane js;

  public Book() {
    icon = new ImageIcon(getClass().getResource("icon.png"));

    this.setIconImage(icon.getImage());

    this.setTitle("Add Books");
    this.setSize(700, 400);
    this.setLocationRelativeTo(null);
    JPanel pn = new JPanel();
    pn.setLayout(null);
    pn.setBackground(Color.WHITE);
    add(pn);

    labeltitle = new JLabel("Add Books");
    labeltitle.setBounds(20, 10, 300, 30);
    labeltitle.setFont(new Font("Arial", Font.BOLD, 16));
    pn.add(labeltitle);
    footerLabel = new JLabel();
    footerLabel.setBounds(20, 330, 200, 30);
    footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String formattedDate = currentDate.format(formatter);

    footerLabel.setText("Current Date: " + formattedDate);

    pn.add(footerLabel);
    label1 = new JLabel("ID");
    label1.setBounds(20, 50, 100, 30);
    pn.add(label1);
    tf1 = new JTextField();
    tf1.setBounds(100, 50, 100, 30);
    pn.add(tf1);

    label2 = new JLabel("TITLE");
    label2.setBounds(20, 90, 100, 30);
    pn.add(label2);

    tf2 = new JTextField();
    tf2.setBounds(100, 90, 180, 30);
    pn.add(tf2);

    pn.add(tf2);

    insertB = new JButton("INSERT");

    insertB.setBounds(20, 130, 120, 30);
    insertB.addActionListener(this);
    insertB.setBackground(Color.decode("#577D86"));
    insertB.setForeground(Color.white);
    pn.add(insertB);

    deleteB = new JButton("DELETE");
    deleteB.setBounds(150, 130, 130, 30);
    deleteB.addActionListener(this);
    deleteB.setBackground(Color.decode("#577D86"));
    deleteB.setForeground(Color.white);
    pn.add(deleteB);

    searchB = new JButton("🔍");
    searchB.setBounds(200, 50, 80, 30);
    searchB.addActionListener(this);
    searchB.setBackground(Color.decode("#577D86"));
    searchB.setForeground(Color.white);
    pn.add(searchB);

    reqB = new JButton("REQUESTS");
    reqB.setBounds(20, 240, 120, 30);
    reqB.addActionListener(this);
    reqB.setBackground(Color.decode("#577D86"));
    reqB.setForeground(Color.white);

    pn.add(reqB);

    modifB = new JButton("MODIFICATIONS");
    modifB.setBounds(20, 205, 260, 30);
    modifB.addActionListener(this);
    modifB.setBackground(Color.decode("#577D86"));
    modifB.setForeground(Color.white);
    pn.add(modifB);

    refreshB = new JButton("REFRESH");
    refreshB.setBounds(20, 280, 120, 30);
    refreshB.addActionListener(this);
    refreshB.setBackground(Color.decode("#577D86"));
    refreshB.setForeground(Color.white);
    pn.add(refreshB);

    subB = new JButton("SUBSCRIBERS");
    subB.setBounds(150, 280, 130, 30);
    subB.addActionListener(this);
    subB.setBackground(Color.decode("#577D86"));
    subB.setForeground(Color.white);
    pn.add(subB);

    borrowB = new JButton("BORROW");
    borrowB.setBounds(150, 240, 130, 30);
    borrowB.addActionListener(this);
    borrowB.setBackground(Color.decode("#577D86"));
    borrowB.setForeground(Color.white);
    pn.add(borrowB);
    DefaultTableModel df = new DefaultTableModel();
    init();
    df.addColumn("IDENTIFIER");
    df.addColumn("TITLE");
    jt.setModel(df);

    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
    cellRenderer.setForeground(Color.decode("#569DAA"));

    for (int i = 0; i < jt.getColumnCount(); i++) {
      jt.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
    }

    pn.add(js);

    String qr = "select * from book";

    try {
      st = cn.connecion().createStatement();
      rst = st.executeQuery(qr);
      while (rst.next()) {
        df.addRow(new Object[] {
          rst.getString("idBook"), rst.getString("title")
        });
      }

    } catch (SQLException ex) {

    }

  }
  public void init() {
    jt = new JTable();
    js = new JScrollPane();
    js.setViewportView(jt);
    js.setBounds(310, 10, 350, 300);
  }

  public static void main(String[] args) {

    Book lv = new Book();
    lv.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == insertB) {
      String a, b;
      a = tf1.getText();
      b = tf2.getText();
      String qr = "insert into Book(idBook,title,available)" +
        "values('" + a + "','" + b + "','OUI')";
      try {
        st = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Insert?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          st.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Insertion success!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Impossible Insertion!", null, JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == deleteB) {
      String a;
      a = tf1.getText();
      String qr = "delete from book where idBook='" + a + "'";
      try {
        st = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "Delete?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          st.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "Success!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Impossible deletion!", null, JOptionPane.ERROR_MESSAGE);
      }
    }
    if (e.getSource() == searchB) {
      String a;
      a = tf1.getText();
      String qr = "select * from book where idBook='" + a + "'";
      try {
        st = cn.connecion().createStatement();
        rst = st.executeQuery(qr);
        if (rst.next()) {
          tf2.setText(rst.getString("Title"));
        } else
          JOptionPane.showMessageDialog(this, "Not found!", null, JOptionPane.ERROR_MESSAGE);
      } catch (SQLException ex) {

      }

    }
    if (e.getSource() == reqB) {
      dispose();
      Request rq = new Request();
      rq.setVisible(true);

    }

    if (e.getSource() == modifB) {
      String a, b;
      a = tf1.getText();
      b = tf2.getText();
      String qr = "update Book set title='" + b + "' where idBook='" + a + "'";
      try {
        st = cn.connecion().createStatement();
        if (JOptionPane.showConfirmDialog(this, "voulez vous modifier?", null, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
          st.executeUpdate(qr);
          JOptionPane.showMessageDialog(this, "modification reussie!");
        }
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erreur,modification impossible!", null, JOptionPane.ERROR_MESSAGE);
      }

    }
    if (e.getSource() == refreshB) {
      dispose();
      Book lv = new Book();
      lv.setVisible(true);

    }

    if (e.getSource() == subB) {

      try {
        dispose();
        Subscriber ab = new Subscriber();
        ab.setVisible(true);

      } catch (Exception ex) {

      }

    }

    if (e.getSource() == borrowB) {
      dispose();
      Borrow ep = new Borrow();
      ep.setVisible(true);
    }

  }
}