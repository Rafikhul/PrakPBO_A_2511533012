package Minggu4;

public class RekeningGiro extends Rekening {
    private double batasOverdraft;

    public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
        super(nomor, nama, saldoAwal, pinAwal);
        this.batasOverdraft = batasOverdraft;
    }

    public double getBatasOverdraft() {
        return batasOverdraft;
    }

    public void setBatasOverdraft(double batasOverdraft) {
        this.batasOverdraft = batasOverdraft;
    }

    @Override
    public void tarikTunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");
        } else if (nominal > (saldo + batasOverdraft)) {
            System.out.printf(localeID, "Transaksi Gagal: Melebihi batas overdraft. Maksimal penarikan: Rp%,.2f%n", (saldo + batasOverdraft));
        } else {
            saldo -= nominal;
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(trxBaru);
            System.out.printf(localeID, "Tarik tunai Giro Rp%,.2f berhasil. Saldo saat ini: Rp%,.2f%n", nominal, saldo);
        }
    }
}