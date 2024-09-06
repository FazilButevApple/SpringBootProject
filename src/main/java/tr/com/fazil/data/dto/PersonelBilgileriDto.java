package tr.com.fazil.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonelBilgileriDto {

    private Integer personelId;
    private String ad;
    private String soyad;
    private Integer yas;
    private String meslekKodu;
    private String meslekAdi;
    private LocalDate meslekGirisTarihi;
}
