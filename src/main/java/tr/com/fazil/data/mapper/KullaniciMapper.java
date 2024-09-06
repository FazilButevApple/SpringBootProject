package tr.com.fazil.data.mapper;

import org.mapstruct.Mapper;
import tr.com.fazil.data.dto.KullaniciDto;
import tr.com.fazil.data.entity.Kullanici;

@Mapper(componentModel = "spring")
public interface KullaniciMapper {

    KullaniciDto toDto(Kullanici kullanici);

    Kullanici toEntity(KullaniciDto kullaniciDto);


}
