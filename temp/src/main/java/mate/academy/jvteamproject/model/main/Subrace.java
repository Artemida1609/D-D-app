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
@Table(name = "subraces")
public class Subrace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "race_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> race;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> description;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "ability_bonuses", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> abilityBonuses;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "racial_traits", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> racialTraits;

    @Column(unique = true, nullable = false)
    private String url;
}
