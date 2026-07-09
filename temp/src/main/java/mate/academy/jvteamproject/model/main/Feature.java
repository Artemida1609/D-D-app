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
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private Integer level;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "class_ref_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> classRef;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "subclass_ref_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> subclassRef;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "prerequisites_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> prerequisites;

    @Convert(converter = StringListConverter.class)
    @Column(name = "description_data", columnDefinition = "LONGTEXT")
    private List<String> description;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "feature_specific_data", columnDefinition = "LONGTEXT")
    private Map<String, Object> featureSpecific;

    private String url;
}
