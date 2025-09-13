/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBOpertemuan4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author Safriya Murni
 */
public class Create {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/PBO_Praktikum_4";
    static final String USER = "postgres";
    static final String PASS = "0000";
    
    static final String QUERY = "CREATE TABLE IF NOT EXISTS penjualan_mobil ("
            + "model VARCHAR(50) PRIMARY KEY, "
            + "nama_mobil VARCHAR(100) NOT NULL, "
            + "tahun_produksi INTEGER, "
            + "harga BIGINT, "
            + "warna VARCHAR(30), "
            + "stok INTEGER"
            + ");";

    public static void main(String[] args) {
        Create create = new Create();
        try (Connection conn = create.getConnection(); 
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate(QUERY);
            System.out.println("Tabel penjualan_mobil berhasil dibuat!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Method ini supaya bisa dipakai juga oleh class turunan (Insert)
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}