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

    static void inputBuku(String kode, String judul, String jenis) {
        Buku b = new Buku(kode, judul, jenis);
        simpan("buku.txt", b.toString());
    }

    static void pinjam(String kodeTransaksi, String nis, String kodeBuku, String tglPinjam, String tglKembali) {

        Transaksi t = new Transaksi(kodeTransaksi, nis, kodeBuku, tglPinjam, "-", 0);
        simpan("transaksi.txt", t.toString());
    }

    static void kembali(String kodeTransaksi, String tglKembali) {
        

        List<String> dataBaru = new ArrayList<>();
        boolean ditemukan = false;

        try (BufferedReader br = new BufferedReader(new FileReader("transaksi.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(" - ");

                if (data[0].equals(kodeTransaksi)) {
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
}