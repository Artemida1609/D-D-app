package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.DropboxUploadResponse;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.mapper.main.ClassMapper;
import mate.academy.jvteamproject.mapper.main.LevelMapper;
import mate.academy.jvteamproject.model.FileResource;
import mate.academy.jvteamproject.model.main.Class;
import mate.academy.jvteamproject.repository.FileResourceRepository;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.LevelRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.dropbox.DropboxService;
import mate.academy.jvteamproject.service.main.ClassService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService, SearchableService {
    private final ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final LevelRepository levelRepository;
    private final LevelMapper levelMapper;
    private final DropboxService dropboxService;
    private final FileResourceRepository fileResourceRepository;

    @Override
    public ClassDto getByOriginalIndex(String index) {
        return classMapper.toDto(classRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<ClassDto> getAll(Pageable pageable) {
        return classRepository.findAll(pageable)
                .map(classMapper::toDto);
    }

    @Override
    public LevelDto getLevelByOriginalIndexAndLevel(String index, int level) {
        return levelMapper.toDto(levelRepository.getLevelByOriginalIndex(index, level));
    }

    @Override
    public Page<LevelDto> getAllLevelsByIndex(String index, Pageable pageable) {
        return levelRepository.getAllLevelsByOriginalIndex(index, pageable)
                .map(levelMapper::toDto);

    }

    @Override
    public ResponseEntity<?> uploadImage(String index, MultipartFile file) {
        Class clas = classRepository.getByOriginalIndex(index);
        String filename = clas.getOriginalIndex() + ".png";
        DropboxUploadResponse response = dropboxService.upload(file, filename);

        FileResource fileResource = new FileResource();
        fileResource.setEntityId(clas.getId());
        fileResource.setImageUrl(response.getImageUrl());
        fileResource.setFileName(filename);
        fileResource.setFileId(response.getFileId());
        fileResourceRepository.save(fileResource);

        clas.setImage(fileResource);
        classRepository.save(clas);

        return ResponseEntity.ok(response.getImageUrl());
    }

    @Override
    public ResponseEntity<byte[]> getImage(String index) {
        Class clas = classRepository.getByOriginalIndex(index);

        FileResource image = clas.getImage();

        byte[] imageBytes = dropboxService.downloadFile(image.getImageUrl());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @Override
    public void deleteImage(String index) {
        Class clas = classRepository.getByOriginalIndex(index);
        clas.setImage(null);
        String filename = "/" + clas.getOriginalIndex() + ".png";
        dropboxService.deleteFile(filename);
        fileResourceRepository.deleteByEntityId(clas.getId());
        classRepository.save(clas);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return classRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "classes",
                        e.getName(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        classMapper.toDto(e)
                ));
    }
}
