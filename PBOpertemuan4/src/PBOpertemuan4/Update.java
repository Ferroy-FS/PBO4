/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBOpertemuan4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Safriya Murni
 */

public class Update extends Create {

    static final String QUERY = "UPDATE penjualan_mobil "
            + "SET stok = 10, harga = 820000000 "
            + "WHERE model = 'F001'";

    public static void main(String[] args) {
        Update update = new Update();
        try (Connection conn = update.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(QUERY);
            System.out.println(rowsAffected + " data berhasil diupdate!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}