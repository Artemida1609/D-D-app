package mate.academy.jvteamproject.model.main;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import mate.academy.jvteamproject.converter.ListMapStringObjectConverter;
import mate.academy.jvteamproject.converter.MapStringObjectConverter;
import mate.academy.jvteamproject.model.FileResource;

@Entity
@Getter
@Setter
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_resource_id")
    private FileResource image;

    private Integer hitDie;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> proficiencyChoices;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> proficiencies;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> savingThrows;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> startingEquipment;
    private String classLevels;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> multiClassing;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> subclasses;

    @Column(unique = true, nullable = false)
    private String url;
}
