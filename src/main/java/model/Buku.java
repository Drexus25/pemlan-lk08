package model;

public class Buku {
    private String judul, kode, jenis;

    public Buku(String judul, String kode, String jenis) {
        this.judul = judul;
        this.kode = kode;
        this.jenis = jenis;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    

    @Override
    public String toString() {
        return kode + " - " + judul + " (" + jenis + ")";
    }
}
