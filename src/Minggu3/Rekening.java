package Minggu3;

import java.util.*;

public class Rekening {

    private String nomorrekening;
    private String namapemilik;
    private double saldo;
    private double saldoAwal;
    private String pin;
    
    ArrayList<Transaksi> riwayatTransaksi;

    Locale localeID = Locale.of("id", "ID");

    public Rekening(String nomor, String nama, double saldoawal, String pinAwal) {
        nomorrekening = nomor;
        namapemilik = nama;
        saldo = saldoawal;
        saldoAwal = saldoawal;
        
        if(pinAwal.length() == 6) {
        	this.pin = pinAwal;
        } else {
        	System.out.println("Peringatan: PIN harus 6 digit! Menggunakan PIN Default 123456");
        	this.pin = "123456";
        }
        
        this.riwayatTransaksi = new ArrayList<>();

        System.out.printf(localeID,"Rekening %s atas nama %s berhasil dibuat dengan saldo awal: Rp%,.2f%n", nomorrekening, namapemilik, saldo);
    }
    
    public String getNomorRekening() {
    	return nomorrekening;
    }
    public String getNamaPemilik() {
    	return namapemilik;
    }
    
    public boolean otentikasi(String inputpin) {
    	return this.pin.equals(inputpin);
    }
    
    public boolean gantipin(String pinlama, String pinbaru) {
    	if(!this.otentikasi(pinlama)) {
    		return false;
    	}
    	
    	if(pinlama.equals(pinbaru)) {
    		return false;
    	}
    	
    	this.pin = pinbaru;
    	return true;
    }

    public void setorTunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
            riwayatTransaksi.add(trxBaru);
            
            System.out.printf(localeID, "Setor tunai Rp%,.2f berhasil. Saldo saat ini Rp%,.2f%n", nominal, saldo);

        } else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    public void tarikTunai(double nominal) {

        if (nominal < 10000) {
            System.out.println("Transaksi Gagal : Minimal nominal penarikan 10.000");

        } else if (nominal > saldo) {
            System.out.printf(localeID, "Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp%,.2f%n", saldo);
        } else {
            saldo -= nominal;
        	String idTrx = "TRX-T-" + System.currentTimeMillis();
        	
        	Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
        	
        	riwayatTransaksi.add(trxBaru);
            System.out.printf(localeID, "Tarik tunai Rp%,.2f berhasil. Saldo Anda: Rp%,.2f%n", nominal, saldo);
        }
    }
    
    public void cetakMutasi() {
    	System.out.println();
    	System.out.println("==========MUTASI REKENING==========");
    	System.out.println("No.Rekening: " + nomorrekening);
    	System.out.println("Nama Pemilik: " + namapemilik);
    	System.out.println("-------------------------");
    	
    	if(riwayatTransaksi.isEmpty()) {
    		System.out.println("Belum ada transaksi direkening ini");
    	} else {
    		int jumlahTransaksi = riwayatTransaksi.size();
    		int jumlahtampil = Math.min(3,jumlahTransaksi);
    		
    		for(int i = jumlahTransaksi - 1;i >= jumlahTransaksi - jumlahtampil; i--) {
    			riwayatTransaksi.get(i).cetakDetail();
    		}	
    	}
    }
    
    public void cetakRingkasanMutasi() {

        double totalSetor = 0;
        double totalTarik = 0;

        for (Transaksi transaksi : riwayatTransaksi) {

            if (transaksi.getJenis().equals("Kredit")) {
                totalSetor += transaksi.getNominal();
            } else if (transaksi.getJenis().equals("Debit")) {
                totalTarik += transaksi.getNominal();
            }
        }

        double akumulasi = totalSetor - totalTarik;

        System.out.println();
        System.out.println("========== RINGKASAN MUTASI ==========");
        System.out.printf(localeID,"Total setor: Rp%,.2f%n",totalSetor);
        System.out.printf(localeID,"Total tarik: Rp%,.2f%n",totalTarik);
        System.out.printf(localeID,"Akumulasi: Rp%,.2f%n",akumulasi);
        System.out.printf(localeID,"Saldo sebelumnya  : Rp%,.2f%n",saldoAwal);
        System.out.printf(localeID,"Saldo saat ini    : Rp%,.2f%n",saldo);
        System.out.println("=======================================");
    }

    public void cekInformasi() {

        System.out.println("---- INFO REKENING ----");
        System.out.println("No. Rekening: " + nomorrekening);
        System.out.println("Nama Pemilik: " + namapemilik);
        System.out.printf(localeID, "Saldo: Rp%,.2f%n", saldo);
        System.out.println("------------------------");
    }
}