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

@Entity
@Getter
@Setter
@Table(name = "levels")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalIndex;
    private Integer level;
    private Integer abilityScoreBonuses;
    private Integer profBonus;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> features;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> spellcasting;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> classSpecific;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> classInfo;

    private String url;
}
