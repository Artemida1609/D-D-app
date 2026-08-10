package mate.academy.jvteamproject.model.main;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.converter.ListMapStringObjectConverter;
import mate.academy.jvteamproject.converter.MapStringObjectConverter;
import mate.academy.jvteamproject.converter.StringListConverter;

@Entity
@Getter
@Setter
@Table(name = "spells")
public class Spell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private String nameUa;
    private Integer level;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "school_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> school;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "classes_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> classes;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "subclasses_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> subclasses;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> description;
    @Column(columnDefinition = "LONGTEXT")
    private List<String> descriptionUa;

    @Convert(converter = StringListConverter.class)
    @Column(name = "higher_level", columnDefinition = "LONGTEXT")
    private List<String> higherLevel;
    @Column(name = "higher_levelUaUa", columnDefinition = "LONGTEXTUaUa")
    private List<String> higherLevelUa;

    @Column(name = "spell_range")
    private String range;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> components;

    @Column(columnDefinition = "LONGTEXT")
    private String material;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean ritual;
    private String duration;
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean concentration;

    @Column(name = "casting_time")
    private String castingTime;

    @Column(name = "attack_type")
    private String attackType;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "damage_type_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> damageType;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "damage_at_slot_level_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> damageAtSlotLevel;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "dc_type_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> dcType;

    @Column(name = "dc_success")
    private String dcSuccess;

    @Column(name = "area_of_effect_type")
    private String areaOfEffectType;

    @Column(name = "area_of_effect_size")
    private Integer areaOfEffectSize;

    @Column(unique = true, nullable = false)
    private String url;
}
