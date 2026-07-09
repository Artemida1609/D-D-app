package mate.academy.jvteamproject.mapper.main.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.mapper.main.MagicItemMapper;
import mate.academy.jvteamproject.model.main.MagicItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T01:45:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class MagicItemMapperImpl implements MagicItemMapper {

    @Override
    public MagicItem toEntity(MagicItemDto magicItemDto) {
        if ( magicItemDto == null ) {
            return null;
        }

        MagicItem magicItem = new MagicItem();

        if ( magicItemDto.getOriginalIndex() != null ) {
            magicItem.setOriginalIndex( magicItemDto.getOriginalIndex() );
        }
        if ( magicItemDto.getName() != null ) {
            magicItem.setName( magicItemDto.getName() );
        }
        List<String> list = magicItemDto.getDescription();
        if ( list != null ) {
            magicItem.setDescription( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = magicItemDto.getEquipmentCategory();
        if ( map != null ) {
            magicItem.setEquipmentCategory( new LinkedHashMap<String, Object>( map ) );
        }
        List<Map<String, Object>> list1 = magicItemDto.getVariants();
        if ( list1 != null ) {
            magicItem.setVariants( new ArrayList<Map<String, Object>>( list1 ) );
        }
        Map<String, Object> map1 = magicItemDto.getRarity();
        if ( map1 != null ) {
            magicItem.setRarity( new LinkedHashMap<String, Object>( map1 ) );
        }
        if ( magicItemDto.getImage() != null ) {
            magicItem.setImage( magicItemDto.getImage() );
        }
        if ( magicItemDto.getUrl() != null ) {
            magicItem.setUrl( magicItemDto.getUrl() );
        }

        return magicItem;
    }

    @Override
    public MagicItemDto toDto(MagicItem magicItem) {
        if ( magicItem == null ) {
            return null;
        }

        MagicItemDto magicItemDto = new MagicItemDto();

        if ( magicItem.getOriginalIndex() != null ) {
            magicItemDto.setOriginalIndex( magicItem.getOriginalIndex() );
        }
        if ( magicItem.getName() != null ) {
            magicItemDto.setName( magicItem.getName() );
        }
        List<String> list = magicItem.getDescription();
        if ( list != null ) {
            magicItemDto.setDescription( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = magicItem.getEquipmentCategory();
        if ( map != null ) {
            magicItemDto.setEquipmentCategory( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = magicItem.getRarity();
        if ( map1 != null ) {
            magicItemDto.setRarity( new LinkedHashMap<String, Object>( map1 ) );
        }
        List<Map<String, Object>> list1 = magicItem.getVariants();
        if ( list1 != null ) {
            magicItemDto.setVariants( new ArrayList<Map<String, Object>>( list1 ) );
        }
        if ( magicItem.getImage() != null ) {
            magicItemDto.setImage( magicItem.getImage() );
        }
        if ( magicItem.getUrl() != null ) {
            magicItemDto.setUrl( magicItem.getUrl() );
        }

        return magicItemDto;
    }
}
