# UTS_PBO_23CNSA_23552011014

#Sistem Kasir Keuangan
Aplikasi kasir keuangan berbasis Java yang mendukung setor dan tarik tunai menggunakan prinsip OOP,koneksi ke database **MySQL (XAMPP), dan dibangun dengan Apache Ant.

##Fitur Utama
-  Setor tunai & tarik tunai
-  Hitung bunga otomatis (dengan Polymorphism)
-  Lihat saldo & riwayat transaksi
-  Koneksi ke database MySQL
-  Format tampilan rupiah
-  Konsep OOP lengkap: Inheritance, Polymorphism, Encapsulation, Interface

##Teknologi
- Java (JDK 8+)
- Apache Ant (build system)
- MySQL (via XAMPP)
- JDBC (Java Database Connectivity)
- CLI Menu Interaktif

##SQL
CREATE DATABASE kasir_keuangan;
USE kasir_keuangan;

CREATE TABLE rekening (
    id INT AUTO_INCREMENT PRIMARY KEY,
    jenis VARCHAR(20),
    saldo DOUBLE
);

CREATE TABLE transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rekening_id INT,
    tipe VARCHAR(10),
    jumlah DOUBLE,
    tanggal TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE nasabah (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    rekening_id INT
);


1. Inheritance** (Pewarisan)      Kelas `Tabungan` dan `Giro` mewarisi (extends) dari kelas `Rekening`. Ini memungkinkan kode seperti `Rekening rekening = new Tabungan
2. 2. Polymorphism** (Polimorfisme)  Metode abstrak `hitungBunga()` dideklarasikan di `Rekening`, dan diimplementasikan berbeda di `Tabungan` dan `Giro`. Ini memungkinkan sistem memanggil `rekening.hitungBunga()` tanpa tahu jenis rekening spesifiknya. 
3. Encapsulation** (Enkapsulasi)  Atribut seperti `id` dan `saldo` di `Rekening` disembunyikan (private/protected) dan diakses melalui method `getSaldo()`, `setor()`, dan `tarik()` untuk menjaga keamanan data.                                        
4. Interface*LayananKeuangan` adalah `interface` yang diterapkan oleh `Rekening`, agar fleksibel terhadap jenis layanan lain (misal nanti: pinjaman, deposito).                                                                    

