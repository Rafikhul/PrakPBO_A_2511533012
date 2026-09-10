package Minggu1;

import java.util.Locale;

public class Rekening {
	String nomorrekening;
	String namapemilik;
	double saldo;

	Locale localeID = Locale.of("id", "ID");

	public Rekening(String nomor, String nama, double saldoawal) {
	    nomorrekening = nomor;
	    namapemilik = nama;
	    saldo = saldoawal;
	    System.out.printf(localeID, "Rekening %s atas nama %s berhasil dibuat dengan saldo awal: Rp%,.2f%n", nomorrekening, namapemilik, saldo);
	} 
	
	public void setorTunai(double nominal) {
		if (nominal > 0) {
			saldo += nominal;
			System.out.printf(localeID, "Setor tunai Rp%,.2f berhasil. Saldo saat ini Rp%,.2f%n", nominal, saldo);
		} else {
			System.out.println("Gagal : Nominal setor harus lebih dari 0!");
		}
	}

	public void cekInformasi() {
	    System.out.println("---- INFO REKENING ----");
	    System.out.println("No. Rekening: " + nomorrekening);
	    System.out.println("Nama Pemilik: " + namapemilik);
	    System.out.printf(localeID, "Saldo: Rp%,.2f%n", saldo);
	    System.out.println("------------------------");
	}
}
