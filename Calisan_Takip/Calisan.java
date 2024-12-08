package Erkan_TURGUT_ÖDEV_calisanSistemi;

import java.time.LocalTime;

// Çalışan sınıfı, çalışanların bilgilerini ve davranışlarını temsil eder...

public class Calisan {
	// private alanlar.(Özellikler)
	private String _sicilNumarasi;
	private String _ad;
	private String _soyad;
	private String _departman;
	private double _maas;
	private String _pozisyon;
	private LocalTime _girisZamani;
	private LocalTime _cikisZamani;
	private double _calismaSaati;

	
	// Constructor Metot: Çalışan sınıfının yeni bir nesnesi oluşturulduğunda çağrılacak
	public Calisan(String sicilNumarasi, String ad, String soyad, String departman, double maas, String pozisyon) {
		_sicilNumarasi = sicilNumarasi;
		_ad = ad;
		_soyad = soyad;
		_departman = departman;
		_maas = maas;
		_pozisyon = pozisyon;
	}
	
	
    // Çalışanın işe giriş yapma işlemini yapan metot
	public void girisYap() {
		_girisZamani = LocalTime.now(); //sistemden mevcut zamanı alır.
		System.out.println(_ad + " " + _soyad + " işe giriş yaptı. Saat: " + _girisZamani);
	}

	
	 // Çalışanın işten çıkış yapma işlemini yapan metot.
    public void cikisYap() {
    	
        // Eğer giriş zamanı ayarlanmışsa çıkış işlemi yapar
        if (_girisZamani != null) {
            // Çıkış zamanı olarak sistemin mevcut saatini akır
            _cikisZamani = LocalTime.now();

            // Giriş ve çıkış zamanı arasındaki fark saat olarak hesaplanır
            _calismaSaati = _girisZamani.until(_cikisZamani, java.time.temporal.ChronoUnit.HOURS);

            System.out.println(_ad + " " + _soyad + " işten çıkış yaptı. Saat: " + _cikisZamani);
            System.out.println("Toplam çalışma süresi = " + _calismaSaati + " saat");
        } else {
            // Eğer giriş yapılmamışsa uyarı mesajı verir.
            System.out.println("Önce işe giriş yapmalısınız");
        }
    }

	@Override
	// Yazdırma işlemi.
	public String toString() {
		return String.format("Çalışan Bilgileri:\n" + "Sicil No: %s\n" + "Ad Soyad: %s %s\n" + "Departman: %s\n"
				+ "Pozisyon: %s\n" + "Maaş: %.2f TL\n", _sicilNumarasi, _ad, _soyad, _departman, _pozisyon, _maas);
	}

	
	//Getter metotları. İLe istediğimiz özellikleri döneriz.
	public String getSicilNumarasi() {
		return _sicilNumarasi;
	}

	public String getAd() {
		return _ad;
	}

	public String getSoyad() {
		return _soyad;
	}

	public String getDepartman() {
		return _departman;
	}
}
