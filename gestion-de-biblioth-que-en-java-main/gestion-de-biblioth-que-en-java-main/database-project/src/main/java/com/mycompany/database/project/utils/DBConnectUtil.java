package com.mycompany.database.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectUtil {
  Connection con;

  public DBConnectUtil() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      //jdbc:mysql://localhost:3306/project_db?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_project_final", "root", "");
      System.out.println("connected Successfully!!");
    } catch (Exception e) {
      System.out.println("Connection Error!!");

    }

  }
  public Connection connecion() {
    return con;
  }
}