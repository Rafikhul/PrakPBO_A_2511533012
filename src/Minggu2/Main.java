package Minggu2;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> daftarRekening = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning = true;
        
        System.out.println("==== SISTEM PERBANKAN MINI ====");
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tarik Tunai");
            System.out.println("4. Cek Informasi Rekening");
            System.out.println("5. Ganti Akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("7. Cetak Ringkasan Mutasi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");
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
                    input.nextLine();
                    
                    Rekening rekeningBaru = new Rekening(no, nama, saldo);

                    daftarRekening.add(rekeningBaru);

                    akunAktif = rekeningBaru;

                    System.out.println("Rekening baru berhasil ditambahkan.");
                    break;
                    
                case 2:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
                    } else {
                        System.out.print("Masukkan nominal setor: ");
                        double setor = input.nextDouble();
                        input.nextLine();
                        akunAktif.setorTunai(setor);
                    }
                    break;

                case 3:
                    if (akunAktif == null) {
                        System.out.println("Error: Mohon maaf, anda belum memiliki nomor rekening!");
                    } else {
                    	System.out.print("Masukkan nominal tarik: ");
                        double tarik = input.nextDouble();
                        input.nextLine();
                        akunAktif.tarikTunai(tarik);
                    }
                    break;

                case 4:
                    if (akunAktif == null) {
                        System.out.println("Error: Anda belum membuka rekening!");
                    } else {
                    	akunAktif.cekInformasi();
                    }
                    break;
                    
                case 5:
                    if (daftarRekening.isEmpty()) {
                        System.out.println("Belum ada rekening yang terdaftar.");
                    } else {
                        System.out.print("Masukkan nomor rekening yang ingin digunakan: ");
                        String nomorCari = input.nextLine();
                        boolean ditemukan = false;
                        
                        for (Rekening rekening : daftarRekening) {
                            if (rekening.nomorrekening.equals(nomorCari)) {
                                akunAktif = rekening;
                                ditemukan = true;
                                System.out.println("Berhasil mengganti akun.");
                                System.out.println("Akun aktif: " + akunAktif.namapemilik);
                               
                                break;
                            }
                        }
                        if (!ditemukan) {
                            System.out.println("Rekening tidak ditemukan.");
                        }
                    }
                    
                    break;
                    
                case 6:
                	akunAktif.cetakMutasi();
                	break;
                	
                case 7:
                	if(akunAktif == null) {
                		System.out.println("Akun belum aktif");
                	} else {
                		akunAktif.cetakRingkasanMutasi();
                	}
                	break;
                    
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terimakasih!");
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        input.close();
    }
}