package mate.academy.jvteamproject.mapper.main;

import mate.academy.jvteamproject.config.MapperConfig;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.mapper.GenericMapper;
import mate.academy.jvteamproject.model.main.Feature;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface FeatureMapper extends GenericMapper<FeatureDto, Feature> {
    Feature toEntity(FeatureDto dto);

    FeatureDto toDto(Feature entity);
}
