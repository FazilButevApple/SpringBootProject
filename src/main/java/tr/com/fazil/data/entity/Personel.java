package tr.com.fazil.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personel", schema = "demo")
public class Personel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ad", length = Integer.MAX_VALUE)
    private String ad;

    @Column(name = "soyad", length = Integer.MAX_VALUE)
    private String soyad;

    @Column(name = "yas")
    private Integer yas;

    @Column(name = "fk_unvan_id")
    private Integer fkUnvanId;

}