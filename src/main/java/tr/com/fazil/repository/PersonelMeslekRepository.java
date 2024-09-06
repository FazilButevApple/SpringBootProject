package tr.com.fazil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.fazil.data.entity.PersonelMeslek;

public interface PersonelMeslekRepository extends JpaRepository<PersonelMeslek,Integer> {
}
