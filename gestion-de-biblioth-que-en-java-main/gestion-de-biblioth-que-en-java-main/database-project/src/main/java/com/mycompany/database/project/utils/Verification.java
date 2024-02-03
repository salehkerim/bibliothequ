package com.mycompany.database.project.utils;

import java.sql.*;

public class Verification {
  ResultSet rst;
  Statement st;
  DBConnectUtil cn = new DBConnectUtil();

  public boolean verifier(String suppliedTitle) {
    boolean test = false;
    String title = null;
    String qr = "select title from book where available='OUI' ";
    try {
      st = cn.connecion().createStatement();
      rst = st.executeQuery(qr);
      while (rst.next() || test == true) {
        title = rst.getString("title");
        if (title.equals(suppliedTitle)) {
          test = true;
        }

      }

    } catch (SQLException ex) {

    }
    return test;

  }

}