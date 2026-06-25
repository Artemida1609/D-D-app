package mate.academy.jvteamproject.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.importer.ImportDefinition;
import mate.academy.jvteamproject.mapper.main.AbilityScoreMapper;
import mate.academy.jvteamproject.mapper.main.ClassMapper;
import mate.academy.jvteamproject.mapper.main.ConditionMapper;
import mate.academy.jvteamproject.mapper.main.DamageTypeMapper;
import mate.academy.jvteamproject.mapper.main.EquipmentMapper;
import mate.academy.jvteamproject.mapper.main.FeatureMapper;
import mate.academy.jvteamproject.mapper.main.LanguageMapper;
import mate.academy.jvteamproject.mapper.main.MagicItemMapper;
import mate.academy.jvteamproject.mapper.main.MagicSchoolMapper;
import mate.academy.jvteamproject.mapper.main.MonsterMapper;
import mate.academy.jvteamproject.mapper.main.ProficiencyMapper;
import mate.academy.jvteamproject.mapper.main.RaceMapper;
import mate.academy.jvteamproject.mapper.main.RuleMapper;
import mate.academy.jvteamproject.mapper.main.RuleSectionMapper;
import mate.academy.jvteamproject.mapper.main.SkillMapper;
import mate.academy.jvteamproject.mapper.main.SpellMapper;
import mate.academy.jvteamproject.mapper.main.SubclassMapper;
import mate.academy.jvteamproject.mapper.main.SubraceMapper;
import mate.academy.jvteamproject.mapper.main.TraitMapper;
import mate.academy.jvteamproject.mapper.main.WeaponPropertyMapper;
import mate.academy.jvteamproject.repository.main.AbilityScoreRepository;
import mate.academy.jvteamproject.repository.main.ClassRepository;
import mate.academy.jvteamproject.repository.main.ConditionRepository;
import mate.academy.jvteamproject.repository.main.DamageTypeRepository;
import mate.academy.jvteamproject.repository.main.EquipmentRepository;
import mate.academy.jvteamproject.repository.main.FeatureRepository;
import mate.academy.jvteamproject.repository.main.LanguageRepository;
import mate.academy.jvteamproject.repository.main.MagicItemRepository;
import mate.academy.jvteamproject.repository.main.MagicSchoolRepository;
import mate.academy.jvteamproject.repository.main.MonsterRepository;
import mate.academy.jvteamproject.repository.main.ProficiencyRepository;
import mate.academy.jvteamproject.repository.main.RaceRepository;
import mate.academy.jvteamproject.repository.main.RuleRepository;
import mate.academy.jvteamproject.repository.main.RuleSectionRepository;
import mate.academy.jvteamproject.repository.main.SkillRepository;
import mate.academy.jvteamproject.repository.main.SpellRepository;
import mate.academy.jvteamproject.repository.main.SubclassRepository;
import mate.academy.jvteamproject.repository.main.SubraceRepository;
import mate.academy.jvteamproject.repository.main.TraitRepository;
import mate.academy.jvteamproject.repository.main.WeaponPropertyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportConfig {

    @Bean
    public List<ImportDefinition<?, ?>> importTasks(
            AbilityScoreMapper abilityScoreMapper,
            AbilityScoreRepository abilityScoreRepo,

            ClassMapper classMapper,
            ClassRepository classRepo,

            ConditionMapper conditionMapper,
            ConditionRepository conditionRepo,

            DamageTypeMapper damageTypeMapper,
            DamageTypeRepository damageTypeRepo,

            EquipmentMapper equipmentMapper,
            EquipmentRepository equipmentRepo,

            FeatureMapper featureMapper,
            FeatureRepository featureRepo,

            LanguageMapper languageMapper,
            LanguageRepository languageRepo,

            MagicItemMapper magicItemMapper,
            MagicItemRepository magicItemRepo,

            MagicSchoolMapper magicSchoolMapper,
            MagicSchoolRepository magicSchoolRepo,

            MonsterMapper monsterMapper,
            MonsterRepository monsterRepo,

            ProficiencyMapper proficiencyMapper,
            ProficiencyRepository proficiencyRepo,

            RaceMapper raceMapper,
            RaceRepository raceRepo,

            RuleMapper ruleMapper,
            RuleRepository ruleRepo,

            RuleSectionMapper ruleSectionMapper,
            RuleSectionRepository ruleSectionRepo,

            SkillMapper skillMapper,
            SkillRepository skillRepo,

            SpellMapper spellMapper,
            SpellRepository spellRepo,

            SubclassMapper subclassMapper,
            SubclassRepository subclassRepo,

            SubraceMapper subraceMapper,
            SubraceRepository subraceRepo,

            TraitMapper traitMapper,
            TraitRepository traitRepo,

            WeaponPropertyMapper weaponPropertyMapper,
            WeaponPropertyRepository weaponPropertyRepo
    ) {
        return List.of(

                new ImportDefinition<>("/api/ability-scores",
                        AbilityScoreDto.class, abilityScoreMapper::toEntity, abilityScoreRepo),
                new ImportDefinition<>("/api/classes",
                        ClassDto.class, classMapper::toEntity, classRepo),
                new ImportDefinition<>("/api/conditions",
                        ConditionDto.class, conditionMapper::toEntity, conditionRepo),
                new ImportDefinition<>("/api/damage-types",
                        DamageTypeDto.class, damageTypeMapper::toEntity, damageTypeRepo),
                new ImportDefinition<>("/api/equipment",
                        EquipmentDto.class, equipmentMapper::toEntity, equipmentRepo),
                new ImportDefinition<>("/api/features",
                        FeatureDto.class, featureMapper::toEntity, featureRepo),
                new ImportDefinition<>("/api/languages",
                        LanguageDto.class, languageMapper::toEntity, languageRepo),
                new ImportDefinition<>("/api/magic-items",
                        MagicItemDto.class, magicItemMapper::toEntity, magicItemRepo),
                new ImportDefinition<>("/api/magic-schools",
                        MagicSchoolDto.class, magicSchoolMapper::toEntity, magicSchoolRepo),
                new ImportDefinition<>("/api/monsters",
                        MonsterDto.class, monsterMapper::toEntity, monsterRepo),
                new ImportDefinition<>("/api/proficiencies",
                        ProficiencyDto.class, proficiencyMapper::toEntity, proficiencyRepo),
                new ImportDefinition<>("/api/races",
                        RaceDto.class, raceMapper::toEntity, raceRepo),
                new ImportDefinition<>("/api/rules",
                        RuleDto.class, ruleMapper::toEntity, ruleRepo),
                new ImportDefinition<>("/api/rule-sections",
                        RuleSectionDto.class, ruleSectionMapper::toEntity, ruleSectionRepo),
                new ImportDefinition<>("/api/skills",
                        SkillDto.class, skillMapper::toEntity, skillRepo),
                new ImportDefinition<>("/api/spells",
                        SpellDto.class, spellMapper::toEntity, spellRepo),
                new ImportDefinition<>("/api/subclasses",
                        SubclassDto.class, subclassMapper::toEntity, subclassRepo),
                new ImportDefinition<>("/api/subraces",
                        SubraceDto.class, subraceMapper::toEntity, subraceRepo),
                new ImportDefinition<>("/api/traits",
                        TraitDto.class, traitMapper::toEntity, traitRepo),
                new ImportDefinition<>("/api/weapon-properties",
                        WeaponPropertyDto.class, weaponPropertyMapper::toEntity, weaponPropertyRepo)
        );
    }
}
