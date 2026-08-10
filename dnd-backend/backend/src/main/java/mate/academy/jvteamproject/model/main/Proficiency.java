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

@Entity
@Getter
@Setter
@Table(name = "proficiencies")
public class Proficiency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private String nameUa;
    private String type;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "classes_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> classes;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "races_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> races;

    @Column(unique = true, nullable = false)
    private String url;
}
