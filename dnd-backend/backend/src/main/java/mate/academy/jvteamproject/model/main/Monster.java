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
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalIndex;
    private String name;
    private String nameUa;
    private String size;
    private String type;
    private String alignment;
    private String alignmentUa;

    @Column(name = "armor_class")
    private Integer armorClass;

    @Column(name = "hit_points")
    private Integer hitPoints;

    @Column(name = "hit_dice")
    private String hitDice;

    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;

    @Column(name = "walk_speed")
    private String walkSpeed;

    @Column(name = "fly_speed")
    private String flySpeed;

    @Column(name = "swim_speed")
    private String swimSpeed;

    @Column(name = "climb_speed")
    private String climbSpeed;

    @Column(name = "burrow_speed")
    private String burrowSpeed;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "proficiencies_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> proficiencies;

    private String blindsight;
    private String darkVision;
    private String tremorSense;
    private String trueSight;
    private Integer passivePerception;

    private String languages;

    @Column(name = "challenge_rating")
    private Double challengeRating;

    private Integer xp;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "actions_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> actions;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "special_abilities_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> specialAbilities;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "legendary_actions_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> legendaryActions;

    @Convert(converter = ListMapStringObjectConverter.class)
    @Column(name = "reactions_data", columnDefinition = "LONGTEXT")
    private List<Map<String, Object>> reactions;

    private String image;

    @Column(unique = true, nullable = false)
    private String url;
}
