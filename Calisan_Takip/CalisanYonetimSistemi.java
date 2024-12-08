package Erkan_TURGUT_ÖDEV_calisanSistemi;

import java.util.ArrayList;
import java.util.Scanner;

public class CalisanYonetimSistemi { // Yönetim Sistemi.
	private static ArrayList<Calisan> calisanListesi = new ArrayList<>(); // Çalışanları Tutacak Olan ArrayList
	private static Scanner scanner = new Scanner(System.in);

	public static void calisanEkle() {
		try { // Hata yakalama.
			System.out.println("Yeni Çalışan Ekleme Formu:");

			System.out.print("Sicil Numarası: ");
			String sicilNumarasi = scanner.nextLine();

			System.out.print("Ad: ");
			String ad = scanner.nextLine();

			System.out.print("Soyad: ");
			String soyad = scanner.nextLine();

			System.out.print("Departman: ");
			String departman = scanner.nextLine();

			System.out.print("Pozisyon: ");
			String pozisyon = scanner.nextLine();
			System.out.print("Aylık Maaş: ");
			double maas = Double.parseDouble(scanner.nextLine());

			// Constructor'a parametre gönderme.
			Calisan yeniCalisan = new Calisan(sicilNumarasi, ad, soyad, departman, maas, pozisyon);
			calisanListesi.add(yeniCalisan);
			System.out.println("Çalışan başarıyla eklendi!");

		} catch (Exception hataMesajı) {
			System.out.println("Hata: Geçersiz girdi. Çalışan eklenemedi. -> " + hataMesajı);// Detaylı bilgi vermek
																								// için hata mesajını
																								// bastırıyoıruz.
		}
	}

	// Metotlar.

	//Çalışan Listeleme.
	public static void calisanlariListele() {

		if (calisanListesi.isEmpty()) {
			System.out.println("Sistemde çalışan bulunmamaktadır.");
			return;
		} else 
		{
			for(Calisan calisanlar : calisanListesi) {
				System.out.println(calisanlar);
			}
		}
	}

	//Departmana Göre Arama.
	public static void departmanaGoreAra(String departman) { //arama yapmak için parametre olarak departman verilir
		boolean bulundu = false;
		for (Calisan calisan : calisanListesi) {
			if (calisan.getDepartman().toLowerCase().contains(departman.toLowerCase())) { //kontrol
				System.out.println(calisan);
				bulundu = true;
			}
		}
		if (!bulundu) {
			System.out.println("Belirtilen departmanda çalışan bulunamadı.");
		}
	}

	
	
	
	//Çalışan silme
	public static void calisanSil(String sicilNumarasi) { //Sicil Numarasına göre silme işlemi yapılır.
		
		boolean silindi = calisanListesi.removeIf(calisan -> calisan.getSicilNumarasi().equals(sicilNumarasi)); //Kontrol True ise Silme işlemini yapar.

		System.out.println(silindi ? "Çalışan silindi." : "Çalışan bulunamadı.");
	}
	
	
	

	//Çalışan Giriş Kaydı tutma.
	public static void calisanGiris() {
		System.out.print("Çalışan Sicil Numarası: "); //Giriş İŞlemini sicil Numarası ile yapıyoruz.
		String sicilNumarasi = scanner.nextLine();

		for (Calisan calisan : calisanListesi) {
			if (calisan.getSicilNumarasi().equals(sicilNumarasi)) {
				calisan.girisYap();//giriş metodunu çlaıştırıyoruz.
				return;
			}
		}
		System.out.println("Çalışan bulunamadı.");
	}

	
	//Çalışan Çıkış Kaydı Tutma.
	public static void calisanCikis() {
		System.out.print("Çalışan Sicil Numarası: ");
		String sicilNumarasi = scanner.nextLine();

		for (Calisan calisan : calisanListesi) {
			if (calisan.getSicilNumarasi().equals(sicilNumarasi)) {
				calisan.cikisYap();
				return;
			}
		}
		System.out.println("Çalışan bulunamadı.");
	}

	
	
	public static void main(String[] args) {
		//Varsayılan olarak iki tane çalışan ekliyroum
		calisanListesi.add(new Calisan("0512", "Aslı", "Aydın", "Yazılım", 150000.0, "Yazılım Geliştirici"));
		calisanListesi.add(new Calisan("12", "Elanur", "Yalçın", "İnsan Kaynakları", 95000.0, "İK Uzmanı"));
		calisanListesi.add(new Calisan("1205", "Erkan", "Turgut", "AI Department", 140000.0, "Senior AI Uzmanı"));
		calisanListesi.add(new Calisan("12", "Serkan", "Turgut", "Mühendislik Bİrimi", 160000.0, "Biyomedikal Mühendisi"));
		calisanListesi.add(new Calisan("12", "Gökhan", "Kutluana", "Eğitim", 180000.0, "Nesneye Dayalı Programlama Eğitmeni"));
		
		
		while (true) { //Çıkış işlemi seçilene kadar Sistemin çalışması için sonsoz döngü içinde kullanıyoruz
			System.out.println("\n--- Çalışan Yönetim Sistemi ---");
			System.out.println("1. Çalışan Ekle");
			System.out.println("2. Tüm Çalışanları Listele");
			System.out.println("3. Departmana Göre Ara");
			System.out.println("4. Sicil Numarası ile Çalışan Sil");
			System.out.println("5. Çalışan Giriş");
			System.out.println("6. Çalışan Çıkış");
			System.out.println("7. Çıkış");

			System.out.print("Seçiminiz: ");
			int tercih = scanner.nextInt(); // Kullanıcıdan seçim alınıyor.
			scanner.nextLine(); // Aradaki newline karakterini temizliyoruz.


			switch (tercih) {
			case 1:
				calisanEkle();
				break;
			case 2:
				calisanlariListele();
				break;
			case 3:
				System.out.print("Aramak istediğiniz departman: ");
				String departman = scanner.nextLine();
				departmanaGoreAra(departman);
				break;
			case 4:
				System.out.print("Silmek istediğiniz sicil numarası: ");
				String sicilNo = scanner.nextLine();
				calisanSil(sicilNo);
				break;
			case 5:
				calisanGiris();
				break;
			case 6:
				calisanCikis();
				break;
			case 7:
				System.out.println("Sistemden çıkılıyor...");
				System.out.println("İşlem Sonlandırıldı....");
				return;
			default:
				System.out.println("Geçersiz seçim. Lütfen Geçerli bir işlem Seçiniz...");
				break;
			}
		}
	}
}
