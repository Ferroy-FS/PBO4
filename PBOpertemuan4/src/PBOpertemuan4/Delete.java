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

public class Delete extends Create {

    static final String QUERY = "DELETE FROM penjualan_mobil "
            + "WHERE model = 'F003'";

    public static void main(String[] args) {
        Delete delete = new Delete();
        try (Connection conn = delete.getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(QUERY);
            System.out.println(rowsAffected + " data berhasil dihapus!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}