package Erkan_TURGUT_ÖDEV_calisanSistemi;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CalisanYonetimSistemi {
    private static ArrayList<Calisan> calisanListesi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    private String dosyaYolu ="C:\\Users\\ERKAN TURGUT\\OneDrive\\Masaüstü\\JavaClassNotes\\MyPersonalNotes\\src\\Erkan_TURGUT_ÖDEV_calisanSistemi\\calisanlar.txt";
    		// Çalışan verilerinin bulunduğu dosya

    
   
    // Dosyadan gelen verilerini oku
    public void dosyadanCalisanOku() {
    	
    	
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) { //dosaya okuma işlemi 
        	
        	
            String line; //dosaydaki her veriyi saklayan satır değişkeni
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); //ayırma işlemini "," e göre tutacak olan dizi.
                String sicilNumarasi = values[0];
                String ad = values[1];
                String soyad = values[2];
                String departman = values[3];
                double maas = Double.parseDouble(values[4]);
                String pozisyon = values[5];

                Calisan calisan = new Calisan(sicilNumarasi, ad, soyad, departman, maas, pozisyon); //ekleme.
                calisanListesi.add(calisan);
            }
            
            System.out.println("Çalışan verileri başarıyla yüklendi....");
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }
    
    
    
    

    // Çalışan ekleme
    public static void calisanEkle() {
        try {
            System.out.print("Sicil Numarası: ");
            String sicilNumarasi = scanner.nextLine();

            // Sicil numarası kontrolü
            for (Calisan calisan : calisanListesi) {
                if (calisan.getSicilNumarasi().equals(sicilNumarasi)) {
                    System.out.println("Bu sicil numarası zaten mevcut!");
                    return;
                }
            }

            System.out.print("Ad: ");
            String ad = scanner.nextLine();

            System.out.print("Soyad: ");
            String soyad = scanner.nextLine();

            System.out.print("Departman: ");
            String departman = scanner.nextLine();

            System.out.print("Pozisyon: ");
            String pozisyon = scanner.nextLine();

            System.out.print("Maaş: ");
            double maas = Double.parseDouble(scanner.nextLine());

            Calisan yeniCalisan = new Calisan(sicilNumarasi, ad, soyad, departman, maas, pozisyon);
            calisanListesi.add(yeniCalisan);
            System.out.println("Çalışan başarıyla eklendi!");
        } catch (Exception e) {
            System.out.println("Hata: Geçersiz giriş. Çalışan eklenemedi!");
        }
    }

    // Çalışanları listeleme
    public static void calisanlariListele() {
        if (calisanListesi.isEmpty()) {
            System.out.println("Sistemde kayıtlı çalışan bulunmamaktadır.");
        } else {
            System.out.println("\n--- Çalışan Listesi ---");
            for (Calisan calisan : calisanListesi) {
                System.out.println(calisan);
            }
        }
    }

    // Departmana göre arama
    public static void departmanaGoreAra() {
        System.out.print("Aramak istediğiniz departman: ");
        String departman = scanner.nextLine();
        boolean bulundu = false;

        for (Calisan calisan : calisanListesi) {
            if (calisan.getDepartman().equalsIgnoreCase(departman)) {
                System.out.println(calisan);
                bulundu = true;
            }
        }

        if (!bulundu) {
            System.out.println("Belirtilen departmanda çalışan bulunamadı.");
        }
    }

    // Çalışan silme
    public static void calisanSil() {
        System.out.print("Silmek istediğiniz sicil numarası: ");
        String sicilNumarasi = scanner.nextLine();
        boolean silindi = calisanListesi.removeIf(calisan -> calisan.getSicilNumarasi().equals(sicilNumarasi));

        System.out.println(silindi ? "Çalışan başarıyla silindi." : "Çalışan bulunamadı.");
    }

    
    
    
    
    // Ana menü
    public static void main(String[] args) {
        CalisanYonetimSistemi sistem = new CalisanYonetimSistemi();
        sistem.dosyadanCalisanOku();

        while (true) {
            System.out.println("\n--- Çalışan Yönetim Sistemi ---");
            System.out.println("1. Çalışan Ekle");
            System.out.println("2. Çalışanları Listele");
            System.out.println("3. Departmana Göre Ara");
            System.out.println("4. Çalışan Sil");
            System.out.println("5. Çıkış");

            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Girişten sonra satır sonu karakterini temizle

            switch (secim) {
                case 1:
                    calisanEkle();
                    break;
                case 2:
                    calisanlariListele();
                    break;
                case 3:
                    departmanaGoreAra();
                    break;
                case 4:
                    calisanSil();
                    break;
                case 5:
                    System.out.println("Sistemden çıkılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }
}
