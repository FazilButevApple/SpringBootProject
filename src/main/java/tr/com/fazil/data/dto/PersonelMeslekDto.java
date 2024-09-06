package tr.com.fazil.data.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import tr.com.fazil.data.entity.Personel;

import java.time.LocalDate;

@Getter
@Setter
public class PersonelMeslekDto {

    private Integer id;
    private Personel personel;
    private String meslekKodu;
    private String meslekAdi;
    private LocalDate meslekGirisTarihi;
}
