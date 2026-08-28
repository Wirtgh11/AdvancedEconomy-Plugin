package com.wirtgh.economy;



import java.io.File;

import java.sql.*;

import java.util.UUID;



public class DatabaseManager {

    private Connection connection;



    public DatabaseManager(File dataFolder) {

        try {

            if (!dataFolder.exists()) {

                dataFolder.mkdirs();

            }

            File dbFile = new File(dataFolder, "economy.db");

            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());

            createTable();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    private void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS players_balance (" +

                "uuid VARCHAR(36) PRIMARY KEY, " +

                "balance DOUBLE DEFAULT 100.0);";

        try (Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public double getBalance(UUID uuid) {

        String sql = "SELECT balance FROM players_balance WHERE uuid = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, uuid.toString());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                return rs.getDouble("balance");

            } else {

                setBalance(uuid, 100.0);

                return 100.0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

            return 0.0;

        }

    }



    public void setBalance(UUID uuid, double amount) {

        String sql = "INSERT INTO players_balance(uuid, balance) VALUES(?, ?) " +

                "ON CONFLICT(uuid) DO UPDATE SET balance = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, uuid.toString());

            pstmt.setDouble(2, amount);

            pstmt.setDouble(3, amount);

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }



    public void close() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}