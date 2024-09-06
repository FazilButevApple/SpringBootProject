package tr.com.fazil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.com.fazil.data.entity.Personel;
import tr.com.fazil.data.projection.PersonelBilgileriProjection;

import java.util.List;

public interface PersonelRepository extends JpaRepository<Personel,Integer> {

    @Query("SELECT p.id as personelId, p.ad as ad, p.soyad as soyad, p.yas as yas , pm.meslekKodu as meslekKodu, pm.meslekAdi as meslekAdi, pm.meslekGirisTarihi as meslekGirisTarihi " +
            "FROM Personel p " +
            "JOIN PersonelMeslek pm on p.id = pm.personel.id " +
            "WHERE p.id = :personelId")
    List<PersonelBilgileriProjection> findPersonelWithMeslekBilgileri(@Param("personelId") Integer personelId);

}
