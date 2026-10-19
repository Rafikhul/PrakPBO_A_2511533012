package Minggu4;

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
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("8. Cetak Ringkasan Mutasi");
            System.out.println("9. Ganti pin");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu : ");

            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1: // TUGAS 1: Pembuatan Multi-Produk
                    System.out.print("Masukkan no rekening: ");
                    String no = input.nextLine();

                    System.out.print("Masukkan nama pemilik: ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan saldo awal: ");
                    double saldo = input.nextDouble();
                    input.nextLine();

                    System.out.print("Masukkan PIN (6 digit): ");
                    String pin = input.nextLine();

                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis");
                    System.out.print("Pilih (1/2): ");
                    int jenisProduk = input.nextInt();
                    input.nextLine();

                    Rekening rekeningBaru = null;

                    if (jenisProduk == 1) {
                        System.out.print("Masukkan suku bunga (%): ");
                        double sukuBunga = input.nextDouble();
                        input.nextLine();
                        rekeningBaru = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
                    } else if (jenisProduk == 2) {
                        System.out.print("Masukkan batas overdraft (limit pinjaman): ");
                        double batasOverdraft = input.nextDouble();
                        input.nextLine();
                        rekeningBaru = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);
                    } else {
                        System.out.println("Pilihan produk tidak valid. Pembuatan rekening dibatalkan.");
                        break;
                    }

                    // Upcasting: Simpan subclass ke ArrayList bertipe Rekening
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
                        System.out.print("Masukkan PIN: ");
                        String pinInput = input.nextLine();

                        if (akunAktif.otentikasi(pinInput)) {

                            System.out.print("Masukkan nominal tarik: ");
                            double tarik = input.nextDouble();
                            input.nextLine();

                            akunAktif.tarikTunai(tarik);

                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
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
                            if (rekening.getNomorRekening().equals(nomorCari)) {
                                akunAktif = rekening;
                                ditemukan = true;
                                System.out.println("Berhasil mengganti akun.");
                                System.out.println("Akun aktif: " + akunAktif.getNamaPemilik());
                                break;
                            }
                        }

                        if (!ditemukan) {
                            System.out.println("Rekening tidak ditemukan.");
                        }
                    }
                    break;

                case 6:
                    if (akunAktif == null) {
                        System.out.println("Akun belum aktif");
                    } else {
                        System.out.print("Masukkan PIN: ");
                        String pinMutasi = input.nextLine();
                        
                        if (akunAktif.otentikasi(pinMutasi)) {
                            akunAktif.cetakMutasi();
                        } else {
                            System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                        }
                    }
                    break;

                case 7: // TUGAS 2: Simulasi Waktu Akhir Bulan
                    if (akunAktif == null) {
                        System.out.println("Akun belum aktif");
                    } else {
                        // Cek apakah akunAktif merupakan objek RekeningTabungan
                        if (akunAktif instanceof RekeningTabungan) {
                            // Downcasting ke RekeningTabungan
                            RekeningTabungan tabungan = (RekeningTabungan) akunAktif;
                            tabungan.tambahBungaAkhirBulan();
                        } else {
                            System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Rekening Tabungan.");
                        }
                    }
                    break;

                case 8:
                    if (akunAktif == null) {
                        System.out.println("Akun belum aktif");
                    } else {
                        akunAktif.cetakRingkasanMutasi();
                    }
                    break;
                    
                case 9:
                    if (akunAktif == null) {
                        System.out.println("Akun belum aktif.");
                    } else {
                        System.out.print("Masukkan PIN lama: ");
                        String pinLama = input.nextLine();

                        if (!akunAktif.otentikasi(pinLama)) {
                            System.out.println("Akses Ditolak: PIN lama salah!");

                        } else {
                            System.out.print("Masukkan PIN baru (6 digit): ");
                            String pinBaru = input.nextLine();
                            
                            System.out.print("Konfirmasi PIN baru: ");
                            String konfirmasiPin = input.nextLine();

                            if (pinLama.equals(pinBaru)) {
                                System.out.println("Gagal: PIN baru tidak boleh sama dengan PIN lama!");

                            } else if (!pinBaru.equals(konfirmasiPin)) {
                                System.out.println("Gagal: Konfirmasi PIN tidak sesuai!");

                            } else if (pinBaru.length() != 6) {
                                System.out.println("Gagal: PIN harus terdiri dari 6 digit!");
                            } else {
                                if (akunAktif.gantipin(pinLama, pinBaru)) {
                                    System.out.println("PIN berhasil diganti!");
                                } else {
                                    System.out.println("Gagal mengganti PIN.");
                                }
                            }
                        }
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