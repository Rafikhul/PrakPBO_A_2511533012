package Minggu3;

import java.util.*;

public class Transaksi {
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	Locale localeID = Locale.of("id", "ID");
	
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public String getIdTransaksi() {
		return idTransaksi;
	}
	public String getJenis() {
		return jenis;
	}
	public double getNominal() {
		return nominal;
	}
	
	public void cetakDetail() {
		System.out.printf(localeID, "ID:  %s | Jenis %s | Nominal: Rp%,.2f%n", idTransaksi, jenis, nominal);
	}
}
