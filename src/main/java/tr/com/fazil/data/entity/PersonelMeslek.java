package tr.com.fazil.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "personel_meslek", schema = "demo")
public class PersonelMeslek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_personel_id", nullable = false)
    private Personel personel;

    @Column(name = "meslek_kodu", nullable = false, length = 50)
    private String meslekKodu;

    @Column(name = "meslek_adi", nullable = false, length = 100)
    private String meslekAdi;

    @Column(name = "meslek_giris_tarihi", nullable = false)
    private LocalDate meslekGirisTarihi;

}