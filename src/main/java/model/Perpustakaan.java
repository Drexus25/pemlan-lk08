package model;

import model.Transaksi;
import model.Siswa;
import model.Buku;
import java.io.*;
import java.util.*;

public class Perpustakaan {
    static Scanner input = new Scanner(System.in);

    static boolean login(String nip, String nama) {
        if (nip.equals("123") && nama.equals("admin")) {
            return true;
        } else {
            System.out.println("Login gagal!");
            return false;
        }
    }

    public static void simpan(String file, String data) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error simpan file: " + e.getMessage());
        }
    }

    static void tampil(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error baca file: " + e.getMessage());
        }
    }

    public static void inputSiswa(String nis, String nama, String alamat) {
       
        Siswa s = new Siswa(nis, nama, alamat);
        simpan("siswa.txt", s.toString());
    }

    static void inputBuku() {
        System.out.print("Kode: ");
        String kode = input.nextLine();

        System.out.print("Judul: ");
        String judul = input.nextLine();

        System.out.print("Jenis: ");
        String jenis = input.nextLine();

        Buku b = new Buku(kode, judul, jenis);
        simpan("buku.txt", b.toString());
    }

    static void pinjam() {
        System.out.print("Kode Transaksi: ");
        String kode = input.nextLine();

        System.out.print("NIS: ");
        String nis = input.nextLine();

        System.out.print("Kode Buku: ");
        String kodeBuku = input.nextLine();

        System.out.print("Tanggal Pinjam: ");
        String tglPinjam = input.nextLine();

        Transaksi t = new Transaksi(kode, nis, kodeBuku, tglPinjam, "-", 0);
        simpan("transaksi.txt", t.toString());
    }

    static void kembali() {
        System.out.print("Kode Transaksi: ");
        String kodeCari = input.nextLine();

        System.out.print("Tanggal Kembali: ");
        String tglKembali = input.nextLine();

        List<String> dataBaru = new ArrayList<>();
        boolean ditemukan = false;

        try (BufferedReader br = new BufferedReader(new FileReader("transaksi.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(" - ");

                if (data[0].equals(kodeCari)) {
                    ditemukan = true;

                    if (data[5].equals("1")) {
                        System.out.println("Buku sudah dikembalikan!");
                        dataBaru.add(line);
                        continue;
                    }

                    data[4] = tglKembali;
                    data[5] = "1";

                    String hasil = String.join(" - ", data);
                    dataBaru.add(hasil);

                    System.out.println("Pengembalian berhasil!");
                } else {
                    dataBaru.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error baca file: " + e.getMessage());
            return;
        }

        if (!ditemukan) {
            System.out.println("Transaksi tidak ditemukan!");
            return;
        }

        try (FileWriter fw = new FileWriter("transaksi.txt")) {
            for (String s : dataBaru) {
                fw.write(s + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error tulis file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        int pilih;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Input Siswa");
            System.out.println("2. Input Buku");
            System.out.println("3. Lihat Siswa");
            System.out.println("4. Lihat Buku");
            System.out.println("5. Pinjam Buku");
            System.out.println("6. Pengembalian Buku");
            System.out.println("7. Lihat Transaksi");
            System.out.println("0. Keluar");

            System.out.print("Pilih: ");
            pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {
               /* case 1: inputSiswa(); break;
                case 2: inputBuku(); break;
                case 3: tampil("siswa.txt"); break;
                case 4: tampil("buku.txt"); break;
                case 5: pinjam(); break;
                case 6: kembali(); break;
                case 7: tampil("transaksi.txt"); break;*/
            }

        } while (pilih != 0);
    }
}