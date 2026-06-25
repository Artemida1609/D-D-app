package mate.academy.jvteamproject.mapper.main;

import java.util.Map;
import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Level;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface LevelMapper extends GenericMapper<LevelDto, Level> {
    Level toEntity(LevelDto dto);

    LevelDto toDto(Level entity);

    @AfterMapping
    default void fillClassIndex(LevelDto dto, @MappingTarget Level entity) {
        Map<String, Object> classInfo = dto.getClassInfo();
        if (classInfo != null) {
            entity.setOriginalIndex((String) classInfo.get("index"));
        }
    }
}
