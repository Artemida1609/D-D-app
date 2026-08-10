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
import mate.academy.jvteamproject.converter.MapStringObjectConverter;
import mate.academy.jvteamproject.converter.StringListConverter;

@Entity
@Getter
@Setter
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private String nameUa;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private List<String> description;
    @Column(columnDefinition = "LONGTEXT")
    private List<String> descriptionUa;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "ability_score_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> abilityScore;

    @Column(unique = true, nullable = false)
    private String url;
}
