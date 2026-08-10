package mate.academy.jvteamproject.service.impl.main;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.DropboxUploadResponse;
import mate.academy.jvteamproject.dto.SearchResult;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.mapper.main.RaceMapper;
import mate.academy.jvteamproject.model.FileResource;
import mate.academy.jvteamproject.model.main.Race;
import mate.academy.jvteamproject.repository.FileResourceRepository;
import mate.academy.jvteamproject.repository.main.RaceRepository;
import mate.academy.jvteamproject.service.SearchableService;
import mate.academy.jvteamproject.service.dropbox.DropboxService;
import mate.academy.jvteamproject.service.main.RaceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService, SearchableService {
    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;
    private final DropboxService dropboxService;
    private final FileResourceRepository fileResourceRepository;

    @Override
    public RaceDto getByOriginalIndex(String index) {
        return raceMapper.toDto(raceRepository.getByOriginalIndex(index));
    }

    @Override
    public Page<RaceDto> getAll(Pageable pageable) {
        return raceRepository.findAll(pageable)
                .map(raceMapper::toDto);
    }

    @Override
    public ResponseEntity<?> uploadImage(String index, MultipartFile file) {
        Race race = raceRepository.getByOriginalIndex(index);
        String filename = race.getOriginalIndex() + ".png";
        DropboxUploadResponse response = dropboxService.upload(file, filename);

        FileResource fileResource = new FileResource();
        fileResource.setEntityId(race.getId());
        fileResource.setImageUrl(response.getImageUrl());
        fileResource.setFileName(filename);
        fileResource.setFileId(response.getFileId());
        fileResourceRepository.save(fileResource);

        race.setImage(fileResource);
        raceRepository.save(race);

        return ResponseEntity.ok(response.getImageUrl());
    }

    @Override
    public ResponseEntity<byte[]> getImage(String index) {
        Race race = raceRepository.getByOriginalIndex(index);

        FileResource image = race.getImage();

        byte[] imageBytes = dropboxService.downloadFile(image.getImageUrl());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(imageBytes);
    }

    @Override
    public void deleteImage(String index) {
        Race race = raceRepository.getByOriginalIndex(index);
        race.setImage(null);
        String filename = race.getOriginalIndex() + ".png";
        fileResourceRepository.deleteByEntityId(race.getId());
        dropboxService.deleteFile(filename);
        raceRepository.save(race);
    }

    @Override
    public Optional<SearchResult> searchByName(String name) {
        return raceRepository.findByNameLike(name).stream()
                .findFirst()
                .map(e -> new SearchResult(
                        "races",
                        e.getName(),
                        e.getNameUa(),
                        e.getOriginalIndex(),
                        e.getUrl(),
                        raceMapper.toDto(e)
                ));
    }
}
