package tr.com.fazil.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "unvan")
public class Unvan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('demo.personel_unvan_id_seq'")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "unvan_kodu", nullable = false, length = 50)
    private String unvanKodu;

    @Column(name = "unvan_adi", nullable = false, length = 100)
    private String unvanAdi;

    @Column(name = "unvan_verilis_tarihi", nullable = false)
    private LocalDate unvanVerilisTarihi;

    @Column(name = "unvan_guncellenme_tarihi")
    private LocalDate unvanGuncellenmeTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unvan_guncelleyen_personel_id")
    private Personel unvanGuncelleyenPersonelId;

}