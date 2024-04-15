/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jga.swordle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDB {
    private Connection connection;

    // Constructor para inicializar la conexión
    public SQLiteDB(String databaseURL) {
        try {
            this.connection = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar una consulta SELECT
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Método para ejecutar una consulta de actualización (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query) {
        int rowsAffected = 0;
        try {
            Statement statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
