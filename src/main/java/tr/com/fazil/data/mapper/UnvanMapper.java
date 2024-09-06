package tr.com.fazil.data.mapper;

import org.mapstruct.Mapper;
import tr.com.fazil.data.dto.PersonelDto;
import tr.com.fazil.data.dto.UnvanDto;
import tr.com.fazil.data.entity.Personel;
import tr.com.fazil.data.entity.Unvan;

@Mapper(componentModel = "spring")
public interface UnvanMapper {

    UnvanDto toDto(Unvan unvan);

    Unvan toEntity(UnvanDto unvanDto);
}
