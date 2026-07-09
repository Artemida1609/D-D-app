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
@Table(name = "traits")
public class Trait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> description;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> races;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> subraces;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> proficiencies;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "trait_specific", columnDefinition = "LONGTEXT")
    private Map<String, Object> traitSpecific;

    @Column(unique = true, nullable = false)
    private String url;
}
