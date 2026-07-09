package mate.academy.jvteamproject.model.main;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.converter.StringListConverter;

@Entity
@Getter
@Setter
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private String type;
    private String script;

    @Convert(converter = StringListConverter.class)
    @Column(name = "description_data", columnDefinition = "LONGTEXT")
    private List<String> description;

    @Convert(converter = StringListConverter.class)
    @Column(name = "typical_speakers_data", columnDefinition = "LONGTEXT")
    private List<String> typicalSpeakers;

    @Column(unique = true, nullable = false)
    private String url;
}
