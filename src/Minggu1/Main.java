package Minggu1;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("==== SISTEM PERBANKAN MINI ====");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1. Buka rekening baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("0. Keluar");
			System.out.println("Pilih menu : ");
			
			int pilihan = input.nextInt();
			input.nextLine();
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan no rekening: ");
				String no = input.nextLine();
				System.out.print("Masukkan nama pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukkan saldo awal: ");
				double saldo = input.nextDouble();
				
				akunAktif = new Rekening(no, nama, saldo);
				break;
				
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
				} else {
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor);
				}
				break;
				
			case 3:
				System.out.println("Fitur ini akan dikerjakan sebagai Tugas Mandiri.");
				break;
			
			case 4:
				if (akunAktif == null) {
					System.out.println("Error : Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terimakasih!");
				break;
				
				default:
					System.out.println("Pilihan tidak valid");
			}
		}
		input.close();
	}

}
