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
@Table(name = "rule_sections")
public class RuleSection {

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

    @Column(unique = true, nullable = false)
    private String url;
}
