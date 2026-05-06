public class Buku {
    String kode, judul, jenis;

    public Buku(String kode, String judul, String jenis) {
        this.kode = kode;
        this.judul = judul;
        this.jenis = jenis;
    }

    public String toString() {
        return kode + " - " + judul + " (" + jenis + ")";
    }
}
