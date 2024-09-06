package tr.com.fazil.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tr.com.fazil.data.dto.UnvanDto;
import tr.com.fazil.data.entity.Unvan;
import tr.com.fazil.data.mapper.UnvanMapper;
import tr.com.fazil.repository.UnvanRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UnvanService {

    private final UnvanRepository unvanRepository;
    private final UnvanMapper unvanMapper;

    public UnvanDto addUnvan(UnvanDto unvanDto) {
        Unvan unvan = unvanMapper.toEntity(unvanDto);
        unvan = unvanRepository.save(unvan);
        return unvanMapper.toDto(unvan);
    }

    public ResponseEntity<UnvanDto> getUnvanById(Integer id) {
        Unvan unvan = unvanRepository.findById(id).orElse(null);
        if (Objects.isNull(unvan)) {
            return ResponseEntity.notFound().build();
        } else {
            UnvanDto unvanDto = unvanMapper.toDto(unvan);
            return ResponseEntity.ok(unvanDto);
        }
    }

    public Page<UnvanDto> getAllUnvans(Pageable pageable) {
        return unvanRepository.findAll(pageable).map(unvanMapper::toDto);
    }

    public UnvanDto updateUnvan(Integer id, UnvanDto unvanDto) {
        Unvan unvan = unvanRepository.findById(id).orElseThrow();
        Unvan unvan1 = unvanMapper.toEntity(unvanDto);
        if (unvan.getId().equals(unvanDto.getId())) {
            Unvan unvan2 = unvanRepository.save(unvan1);
            return unvanMapper.toDto(unvan2);
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteUnvan(Integer id) {
        unvanRepository.deleteById(id);
    }


}
