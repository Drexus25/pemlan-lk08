import java.io.Serializable;

public class Siswa implements Serializable {
    String nis, nama, alamat;

    public Siswa(String nis, String nama, String alamat) {
        this.nis = nis;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String toString() {
        return nis + " - " + nama + " - " + alamat;
    }
}
