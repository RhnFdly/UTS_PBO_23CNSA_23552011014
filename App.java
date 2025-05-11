/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kasir;

import com.kasir.model.Rekening;
import com.kasir.model.Tabungan;
import com.kasir.koneksi.DatabaseConnection;

import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class App {
    static Scanner input = new Scanner(System.in);
    static Rekening rekening = new Tabungan(1, 1000000); // sementara ID=1
    static final int rekeningId = 1;

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== SISTEM KASIR KEUANGAN ===");
            System.out.println("1. Lihat Saldo");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1 -> lihatSaldo();
                case 2 -> setorTunai();
                case 3 -> tarikTunai();
                case 4 -> lihatRiwayat();
                case 5 -> System.out.println("Terima kasih telah menggunakan sistem.");
                default -> System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 5);
    }

    static void lihatSaldo() {
        System.out.println("Saldo saat ini: " + formatRupiah(rekening.getSaldo()));
        System.out.println("Bunga dihitung: " + formatRupiah(rekening.hitungBunga()));
    }

    static void setorTunai() {
        System.out.print("Masukkan jumlah setor: ");
        double jumlah = input.nextDouble();
        if (jumlah <= 0) {
            System.out.println("Jumlah tidak valid.");
            return;
        }
        rekening.setor(jumlah);
        simpanTransaksi("setor", jumlah);
        System.out.println("Setor berhasil. Saldo sekarang: " + formatRupiah(rekening.getSaldo()));
    }

    static void tarikTunai() {
        System.out.print("Masukkan jumlah tarik: ");
        double jumlah = input.nextDouble();
        if (jumlah <= 0) {
            System.out.println("Jumlah tidak valid.");
            return;
        }
        if (rekening.getSaldo() < jumlah) {
            System.out.println("Saldo tidak cukup.");
            return;
        }
        rekening.tarik(jumlah);
        simpanTransaksi("tarik", jumlah);
        System.out.println("Tarik berhasil. Saldo sekarang: " + formatRupiah(rekening.getSaldo()));
    }

    static void lihatRiwayat() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM transaksi WHERE rekening_id = ? ORDER BY tanggal DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rekeningId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n--- Riwayat Transaksi ---");
            while (rs.next()) {
                String tipe = rs.getString("tipe");
                double jumlah = rs.getDouble("jumlah");
                Timestamp waktu = rs.getTimestamp("tanggal");

                System.out.printf("%s\t%s\t%s\n", waktu, tipe.toUpperCase(), formatRupiah(jumlah));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan riwayat: " + e.getMessage());
        }
    }

    static void simpanTransaksi(String tipe, double jumlah) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO transaksi (rekening_id, tipe, jumlah) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rekeningId);
            stmt.setString(2, tipe);
            stmt.setDouble(3, jumlah);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal menyimpan transaksi: " + e.getMessage());
        }
    }

    static String formatRupiah(double amount) {
        Locale indo = new Locale("id", "ID");
        return NumberFormat.getCurrencyInstance(indo).format(amount);
    }
}
