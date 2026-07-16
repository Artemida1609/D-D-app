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
import mate.academy.jvteamproject.converter.IntegerListConverter;
import mate.academy.jvteamproject.converter.ListMapStringObjectConverter;
import mate.academy.jvteamproject.model.FileResource;

@Entity
@Getter
@Setter
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_resource_id")
    private FileResource image;

    private Integer speed;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "ability_bonus_ability_score_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> abilityBonusAbilityScore;

    @Convert(converter = IntegerListConverter.class)
    @Column(name = "ability_bonus_value_data", columnDefinition = "LONGTEXT")
    private List<Integer> abilityBonusValue;

    @Column(columnDefinition = "LONGTEXT")
    private String alignment;

    @Column(columnDefinition = "LONGTEXT")
    private String age;

    private String size;

    @Column(name = "size_description", columnDefinition = "LONGTEXT")
    private String sizeDescription;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "starting_proficiencies_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> startingProficiencies;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "languages_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> languages;

    @Column(name = "language_desc", columnDefinition = "LONGTEXT")
    private String languageDesc;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "traits_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> traits;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "subraces_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> subraces;

    @Column(unique = true, nullable = false)
    private String url;
}
