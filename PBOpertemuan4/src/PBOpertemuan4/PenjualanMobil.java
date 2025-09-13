/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PBOpertemuan4;

/**
 *
 * @author LEGION
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PenjualanMobil extends Create {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PenjualanMobil app = new PenjualanMobil();
        app.showMenu();
    }

    public void showMenu() {
        int pilihan;
        do {
            System.out.println("\n=== DATA PENJUALAN MOBIL ===");
            System.out.println("Silahkan masukkan angka dari 0 hingga 5");
            System.out.println("");
            System.out.println("1. Lihat Data Mobil");
            System.out.println("2. Tambah Data Mobil");
            System.out.println("3. Ubah Data Mobil");
            System.out.println("4. Hapus Data Mobil");
            System.out.println("5. Buat Tabel Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");

            pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    ShowAllCars();
                    break;
                case 2:
                    AddNewCar();
                    break;
                case 3:
                    UpdateCar();
                    break;
                case 4:
                    DeleteCar();
                    break;
                case 5:
                    CreateTable();
                    break;
                case 0:
                    System.out.println("Program selesai. Data tersimpan di database.");
                    break;
                default:
                    System.out.println("Silahkan masukkan angka dari 0 hingga 5");
            }
        } while (pilihan != 0);
    }

    public void ShowAllCars() {
        System.out.println("\n=== DAFTAR MOBIL ===");
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM penjualan_mobil")) {

            if (!rs.isBeforeFirst()) {
                System.out.println("Tidak ada data mobil.");
                return;
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
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void AddNewCar() {
        System.out.println("\n=== TAMBAH MOBIL BARU ===");

        System.out.print("Model (kode unik): ");
        String model = scanner.nextLine();

        System.out.print("Nama Mobil: ");
        String nama = scanner.nextLine();

        System.out.print("Tahun Produksi: ");
        int tahun = scanner.nextInt();

        System.out.print("Harga: ");
        long harga = scanner.nextLong();
        scanner.nextLine(); 

        System.out.print("Warna: ");
        String warna = scanner.nextLine();

        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        String query = String.format(
                "INSERT INTO penjualan_mobil (model, nama_mobil, tahun_produksi, harga, warna, stok) "
                + "VALUES('%s', '%s', %d, %d, '%s', %d)",
                model, nama, tahun, harga, warna, stok);

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " data berhasil ditambahkan!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void UpdateCar() {
        System.out.println("\n=== UBAH DATA MOBIL ===");

        System.out.print("Masukkan model mobil yang akan diubah: ");
        String model = scanner.nextLine();

        if (!isCarExists(model)) {
            System.out.println("Mobil dengan model " + model + " tidak ditemukan!");
            return;
        }

        System.out.println("\nMasukkan data baru (kosongkan/0/-1 jika tidak ingin mengubah):");

        System.out.print("Nama Mobil: ");
        String nama = scanner.nextLine();

        System.out.print("Tahun Produksi (0 jika tidak ingin mengubah): ");
        int tahun = scanner.nextInt();

        System.out.print("Harga (0 jika tidak ingin mengubah): ");
        long harga = scanner.nextLong();
        scanner.nextLine(); // consume newline

        System.out.print("Warna: ");
        String warna = scanner.nextLine();

        System.out.print("Stok (-1 jika tidak ingin mengubah): ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // consume newline

        StringBuilder queryBuilder = new StringBuilder("UPDATE penjualan_mobil SET ");
        boolean hasUpdate = false;

        if (!nama.isEmpty()) {
            queryBuilder.append("nama_mobil = '").append(nama).append("', ");
            hasUpdate = true;
        }
        if (tahun > 0) {
            queryBuilder.append("tahun_produksi = ").append(tahun).append(", ");
            hasUpdate = true;
        }
        if (harga > 0) {
            queryBuilder.append("harga = ").append(harga).append(", ");
            hasUpdate = true;
        }
        if (!warna.isEmpty()) {
            queryBuilder.append("warna = '").append(warna).append("', ");
            hasUpdate = true;
        }
        if (stok >= 0) {
            queryBuilder.append("stok = ").append(stok).append(", ");
            hasUpdate = true;
        }

        if (!hasUpdate) {
            System.out.println("Tidak ada data yang diubah.");
            return;
        }

        String query = queryBuilder.substring(0, queryBuilder.length() - 2);
        query += " WHERE model = '" + model + "'";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " data berhasil diupdate!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void DeleteCar() {
        System.out.println("\n=== HAPUS DATA MOBIL ===");

        System.out.print("Masukkan model mobil yang akan dihapus: ");
        String model = scanner.nextLine();

        System.out.print("Apakah Anda yakin ingin menghapus mobil dengan model " + model + "? (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Penghapusan dibatalkan.");
            return;
        }

        String query = "DELETE FROM penjualan_mobil WHERE model = '" + model + "'";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Data berhasil dihapus!");
            } else {
                System.out.println("Data tidak ditemukan!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void CreateTable() {
        System.out.println("\n=== BUAT TABEL BARU ===");

        System.out.print("Perhatian! Jika tabel sudah ada, akan dihapus dan dibuat baru. Lanjutkan? (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Pembuatan tabel dibatalkan.");
            return;
        }

        String dropQuery = "DROP TABLE IF EXISTS penjualan_mobil";
        String createQuery = "CREATE TABLE penjualan_mobil ("
                + "model varchar(50) PRIMARY KEY, "
                + "nama_mobil varchar(100) NOT NULL, "
                + "tahun_produksi integer, "
                + "harga bigint, "
                + "warna varchar(30), "
                + "stok integer"
                + ")";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(dropQuery);
            stmt.executeUpdate(createQuery);
            System.out.println("Tabel penjualan_mobil berhasil dibuat!");

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private boolean isCarExists(String model) {
        String query = "SELECT 1 FROM penjualan_mobil WHERE model = '" + model + "'";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
