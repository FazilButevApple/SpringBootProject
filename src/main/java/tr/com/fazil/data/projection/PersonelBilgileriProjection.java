package tr.com.fazil.data.projection;

import java.time.LocalDate;

public interface PersonelBilgileriProjection {

    Integer getPersonelId();
    String getAd();
    String getSoyad();
    Integer getYas();
    String getMeslekKodu();
    String getMeslekAdi();
    LocalDate getMeslekGirisTarihi();

}
