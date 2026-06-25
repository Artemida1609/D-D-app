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
@Table(name = "subclasses")
public class Subclass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;

    @Column(name = "subclass_flavor")
    private String subclassFlavor;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "class_ref_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> classRef;

    @Convert(converter = StringListConverter.class)
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private List<String> description;

    @Column(name = "subclass_levels")
    private String subclassLevels;

    @Column(unique = true, nullable = false)
    private String url;
}
