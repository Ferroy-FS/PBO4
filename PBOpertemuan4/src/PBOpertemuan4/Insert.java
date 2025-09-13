/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Safriya Murni
 */
package PBOpertemuan4;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert extends Create { 

    static final String QUERY = "INSERT INTO penjualan_mobil (model, nama_mobil, tahun_produksi, harga, warna, stok) "
            + "VALUES "
            + "('F001', 'Ford Mustang', 2023, 850000000, 'Merah', 5), "
            + "('F002', 'Ford Ranger', 2023, 650000000, 'Hitam', 8), "
            + "('F003', 'Ford Focus', 2023, 450000000, 'Putih', 3);";

    public static void main(String[] args) {
        Insert insert = new Insert();
        try (Connection conn = insert.getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            int rowsAffected = stmt.executeUpdate(QUERY);
            System.out.println(rowsAffected + " data berhasil ditambahkan!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}