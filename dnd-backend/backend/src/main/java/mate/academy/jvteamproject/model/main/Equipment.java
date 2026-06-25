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
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalIndex;
    private String name;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> description;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> equipmentCategory;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> gearCategory;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> cost;

    private Double weight;

    private String armorCategory;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> armorClass;

    private String capacity;
    private String categoryRange;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> contents;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> damage;

    private String image;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<Map<String, Object>> properties;

    private Integer quantity;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(name = "range_data", columnDefinition = "TEXT")
    private Map<String, Object> range;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "TEXT")
    private List<String> special;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> speed;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean stealthDisadvantage;

    private Integer strMinimum;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> throwRange;

    private String toolCategory;

    @Convert(converter = MapStringObjectConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> twoHandedDamage;

    private String vehicleCategory;

    private String weaponCategory;

    private String weaponRange;
    private String url;
}

