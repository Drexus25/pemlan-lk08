package controller;

import java.io.*;
import java.util.*;
import model.Buku;
import model.Siswa;
import model.Transaksi;

public class Perpustakaan {
    static final String PATH = "src/main/resources/";

    public static void simpan(String file, String data) {
        File f = new File(PATH + file);
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            System.out.println("Error simpan file: " + e.getMessage());
        }
    }

    static void tampil(String file) {
        File f = new File(PATH + file);
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
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

    public static void inputBuku(String judul, String kode, String jenis) {
        Buku b = new Buku(judul, kode, jenis);
        simpan("buku.txt", b.toString());
    }

    public static void pinjam(String kodeTransaksi, String nis, String kodeBuku, String tglPinjam, String tglKembali, String petugas) {

        Transaksi t = new Transaksi(kodeTransaksi, nis, kodeBuku, tglPinjam, "-", petugas, 0);
        simpan("transaksi.txt", t.toString());
    }

    public static void kembali(String kodeTransaksi, String tglKembali) {

    List<String> dataBaru = new ArrayList<>();
    boolean ditemukan = false;

    try (BufferedReader br = new BufferedReader(
            new FileReader("src/main/resources/transaksi.txt"))) {

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

    try (FileWriter fw = new FileWriter(
            "src/main/resources/transaksi.txt")) {

        for (String s : dataBaru) {

            fw.write(s + "\n");
        }

    } catch (IOException e) {

        System.out.println("Error tulis file: " + e.getMessage());
    }
}
}