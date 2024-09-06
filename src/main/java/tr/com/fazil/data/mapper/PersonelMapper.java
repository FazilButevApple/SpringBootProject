package tr.com.fazil.data.mapper;

import org.mapstruct.Mapper;
import tr.com.fazil.data.dto.PersonelDto;
import tr.com.fazil.data.entity.Personel;

@Mapper(componentModel = "spring")
public interface PersonelMapper {

    //@Mapping(source = "id", target = "personelId")
    PersonelDto toDto(Personel personel);

    //@Mapping(source = "personelId", target = "id")
    Personel toEntity(PersonelDto personelDto);


}
