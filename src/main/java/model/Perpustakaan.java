package model;

import model.Transaksi;
import model.Siswa;
import model.Buku;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Perpustakaan {
    static Scanner input = new Scanner(System.in);
    private static final Path DATA_DIR = Paths.get(System.getProperty("user.dir"), "src", "main", "resources");

    private static Path dataFile(String file) {
        return DATA_DIR.resolve(file);
    }

    static boolean login(String nip, String nama) {
        if (nip.equals("123") && nama.equals("admin")) {
            return true;
        } else {
            System.out.println("Login gagal!");
            return false;
        }
    }

    public static void simpan(String file, String data) {
        try {
            Files.createDirectories(DATA_DIR);
            try (FileWriter fw = new FileWriter(dataFile(file).toString(), true)) {
                fw.write(data + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error simpan file: " + e.getMessage());
        }
    }

    static void tampil(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile(file).toString()))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error baca file: " + e.getMessage());
        }
    }

    public static String baca(String file) {
        Path path = dataFile(file);
        if (!Files.exists(path)) {
            return "";
        }

        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println("Error baca file: " + e.getMessage());
            return "";
        }
    }

    public static String pinjamBuku(String kode, String nis, String kodeBuku, String tglPinjam) {
        try {
            Transaksi t = new Transaksi(kode, nis, kodeBuku, tglPinjam, "-", 0);
            simpan("transaksi.txt", t.toString());
            return "Peminjaman berhasil!";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String kembalikanBuku(String kodeCari, String tglKembali) {
        List<String> dataBaru = new ArrayList<>();
        boolean ditemukan = false;

        try (BufferedReader br = new BufferedReader(new FileReader(dataFile("transaksi.txt").toString()))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(" - ");

                if (data[0].equals(kodeCari)) {
                    ditemukan = true;

                    if (data[5].equals("1")) {
                        return "Buku sudah dikembalikan!";
                    }

                    data[4] = tglKembali;
                    data[5] = "1";

                    String hasil = String.join(" - ", data);
                    dataBaru.add(hasil);
                } else {
                    dataBaru.add(line);
                }
            }

        } catch (IOException e) {
            return "Error baca file: " + e.getMessage();
        }

        if (!ditemukan) {
            return "Transaksi tidak ditemukan!";
        }

        try (FileWriter fw = new FileWriter(dataFile("transaksi.txt").toString())) {
            for (String s : dataBaru) {
                fw.write(s + "\n");
            }
        } catch (IOException e) {
            return "Error tulis file: " + e.getMessage();
        }

        return "Pengembalian berhasil!";
    }

    public static void inputSiswa(String nis, String nama, String alamat) {
       
        Siswa s = new Siswa(nis, nama, alamat);
        simpan("siswa.txt", s.toString());
    }

    public static void inputBuku(String kode, String judul, String jenis) {
        Buku b = new Buku(kode, judul, jenis);
        simpan("buku.txt", b.toString());
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