package Minggu2;

import java.util.*;

public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	Locale localeID = Locale.of("id", "ID");
	
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public void cetakDetail() {
		System.out.printf(localeID, "ID:  %s | Jenis %s | Nominal: Rp%,.2f%n", idTransaksi, jenis, nominal);
	}
}
