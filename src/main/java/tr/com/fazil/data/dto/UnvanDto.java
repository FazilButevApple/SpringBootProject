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
public class UnvanDto {

    private Integer id;
    private String unvanKodu;
    private String unvanAdi;
    private LocalDate unvanVerilisTarihi;
    private LocalDate unvanGuncellenmeTarihi;
    private Personel unvanGuncelleyenPersonelId;

}
