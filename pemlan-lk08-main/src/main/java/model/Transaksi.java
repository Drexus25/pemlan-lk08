package model;

public class Transaksi {
    String kode, nis, kodeBuku, tglPinjam, tglKembali, petugas;
    int status;

    public Transaksi(String kode, String nis, String kodeBuku, String tglPinjam, String tglKembali, String petugas, int status) {
        this.kode = kode;
        this.nis = nis;
        this.kodeBuku = kodeBuku;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.petugas = petugas;
        this.status = status;
    }

    public String toString() {
        return kode + " - " + nis + " - " + kodeBuku + " - " + tglPinjam + " - " + tglKembali + " - " + status + " - " + petugas;
    }
}