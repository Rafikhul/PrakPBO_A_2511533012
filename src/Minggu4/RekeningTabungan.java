package Minggu4;

public class RekeningTabungan extends Rekening {
    private double sukuBunga;

    public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
        super(nomor, nama, saldoAwal, pinAwal);
        this.sukuBunga = sukuBunga;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public void setSukuBunga(double sukuBunga) {
        this.sukuBunga = sukuBunga;
    }

    public void tambahBungaAkhirBulan() {
        double bunga = saldo * (sukuBunga / 100);
        setorTunai(bunga);
        System.out.printf(localeID, "Simulasi Akhir Bulan: Penambahan bunga sebesar Rp%,.2f (Suku Bunga: %.2f%%)%n", bunga, sukuBunga);
    }
}