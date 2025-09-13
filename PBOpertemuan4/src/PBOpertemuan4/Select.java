/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PBOpertemuan4;

/**
 *
 * @author Safriya Murni
 */ 

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select extends Create {

    static final String QUERY = "SELECT * FROM penjualan_mobil";

    public static void main(String[] args) {
        Select select = new Select();
        try (Connection conn = select.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY)) {

            System.out.println("=== DATA MOBIL ===");

            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ada data ditemukan!");
            }

            while (rs.next()) {
                System.out.println("Model: " + rs.getString("model"));
                System.out.println("Nama Mobil: " + rs.getString("nama_mobil"));
                System.out.println("Tahun: " + rs.getInt("tahun_produksi"));
                System.out.println("Harga: Rp " + rs.getLong("harga"));
                System.out.println("Warna: " + rs.getString("warna"));
                System.out.println("Stok: " + rs.getInt("stok"));
                System.out.println("-----------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}