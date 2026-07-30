package mate.academy.jvteamproject.helper;

import java.time.LocalDateTime;
import java.util.Set;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.dto.equipment.EquipmentCategoryDto;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.dto.skill.SkillDto;
import mate.academy.jvteamproject.dto.spell.SpellDto;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.dto.user.UserDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationRequestDto;
import mate.academy.jvteamproject.dto.user.UserRegistrationResponseDto;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.model.Role;
import mate.academy.jvteamproject.model.SavedElement;
import mate.academy.jvteamproject.model.User;
import mate.academy.jvteamproject.model.main.*;
import mate.academy.jvteamproject.model.main.Class;

public final class TestDataHelper {
    public TestDataHelper() {

    }

    public static SavedElement createSavedElement(Long id, Long userId, String entityType, Long entityId, LocalDateTime savedAt) {
        SavedElement savedElement = new SavedElement();
        savedElement.setId(id);
        savedElement.setUserId(userId);
        savedElement.setEntityType(entityType);
        savedElement.setEntityId(entityId);
        savedElement.setSavedAt(savedAt);

        return savedElement;
    }

    public static SavedElementResponseDto createSavedElementResponseDto(Long id, String entityType, Long entityId, LocalDateTime savedAt) {
        SavedElementResponseDto responseDto = new SavedElementResponseDto();
        responseDto.setId(id);
        responseDto.setEntityType(entityType);
        responseDto.setEntityId(entityId);
        responseDto.setSavedAt(savedAt);

        return responseDto;
    }

    public static SavedElementRequestDto createSavedElementRequestDto(String entityType, Long entityId) {
        SavedElementRequestDto requestDto = new SavedElementRequestDto();
        requestDto.setEntityType(entityType);
        requestDto.setEntityId(entityId);

        return requestDto;
    }

    public static UserRegistrationRequestDto createUserRegistrationRequestDto(String email, String password, String repeatPassword, String userNickname) {
        UserRegistrationRequestDto userRegistrationRequestDto = new UserRegistrationRequestDto();
        userRegistrationRequestDto.setEmail(email);
        userRegistrationRequestDto.setPassword(password);
        userRegistrationRequestDto.setRepeatPassword(repeatPassword);
        userRegistrationRequestDto.setUserNickname(userNickname);

        return userRegistrationRequestDto;
    }

    public static UserRegistrationResponseDto createUserRegistrationResponseDto(Long id, String email, String userNickname) {
        UserRegistrationResponseDto userRegistrationResponseDto = new UserRegistrationResponseDto();
        userRegistrationResponseDto.setId(id);
        userRegistrationResponseDto.setEmail(email);
        userRegistrationResponseDto.setUserNickname(userNickname);

        return userRegistrationResponseDto;
    }
    public static User createUser(String email,
                                  String password, String userNickname) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserNickname(userNickname);

        return user;
    }

    public static UserDto createDto(String email, String userNickname) {
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setUserNickname(userNickname);

        return userDto;
    }

    public static Role createRole(Long id, Role.RoleName  roleName) {
        Role role = new Role();
        role.setId(id);
        role.setName(roleName);

        return role;
    }

    public static AbilityScore createAbilityScore(String originalIndex, String name) {
        AbilityScore abilityScore = new AbilityScore();
        abilityScore.setName(name);
        abilityScore.setOriginalIndex(originalIndex);

        return abilityScore;
    }

    public static AbilityScoreDto createAbilityScoreDto(String originalIndex, String name) {
        AbilityScoreDto abilityScoreDto = new AbilityScoreDto();
        abilityScoreDto.setName(name);
        abilityScoreDto.setOriginalIndex(originalIndex);

        return abilityScoreDto;
    }

    public static Class createClass(String originalIndex, String name) {
        Class classEntity = new Class();
        classEntity.setName(name);
        classEntity.setOriginalIndex(originalIndex);

        return classEntity;
    }

    public static ClassDto createClassDto(String originalIndex, String name) {
        ClassDto classDto = new ClassDto();
        classDto.setName(name);
        classDto.setOriginalIndex(originalIndex);

        return classDto;
    }

    public static Condition createCondition(String originalIndex, String name) {
        Condition condition = new Condition();
        condition.setName(name);
        condition.setOriginalIndex(originalIndex);

        return condition;
    }

    public static ConditionDto createConditionDto(String originalIndex, String name) {
        ConditionDto conditionDto = new ConditionDto();
        conditionDto.setName(name);
        conditionDto.setOriginalIndex(originalIndex);

        return conditionDto;
    }

    public static DamageType createDamageType(String originalIndex, String name) {
        DamageType damageType = new DamageType();
        damageType.setName(name);
        damageType.setOriginalIndex(originalIndex);

        return damageType;
    }

    public static DamageTypeDto createDamageTypeDto(String originalIndex, String name) {
        DamageTypeDto damageTypeDto = new DamageTypeDto();
        damageTypeDto.setName(name);
        damageTypeDto.setOriginalIndex(originalIndex);

        return damageTypeDto;
    }

    public static EquipmentCategory createEquipmentCategory(String originalIndex, String globalCategory) {
        EquipmentCategory equipmentCategory = new EquipmentCategory();
        equipmentCategory.setGlobalCategory(globalCategory);
        equipmentCategory.setOriginalIndex(originalIndex);

        return equipmentCategory;
    }

    public static EquipmentCategoryDto createEquipmentCategoryDto(String originalIndex, String globalCategory) {
        EquipmentCategoryDto equipmentCategoryDto = new EquipmentCategoryDto();
        equipmentCategoryDto.setGlobalCategory(globalCategory);
        equipmentCategoryDto.setOriginalIndex(originalIndex);

        return equipmentCategoryDto;
    }

    public static Equipment createEquipment(String originalIndex, String name) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setOriginalIndex(originalIndex);

        return equipment;
    }

    public static EquipmentDto createEquipmentDto(String originalIndex, String name) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setName(name);
        equipmentDto.setOriginalIndex(originalIndex);

        return equipmentDto;
    }

    public static Feature createFeature(String originalIndex, String name) {
        Feature feature = new Feature();
        feature.setName(name);
        feature.setOriginalIndex(originalIndex);

        return feature;
    }

    public static FeatureDto createFeatureDto(String originalIndex, String name) {
        FeatureDto featureDto = new FeatureDto();
        featureDto.setName(name);
        featureDto.setOriginalIndex(originalIndex);

        return featureDto;
    }

    public static Language createLanguage(String originalIndex, String name) {
        Language language = new Language();
        language.setName(name);
        language.setOriginalIndex(originalIndex);

        return language;
    }

    public static LanguageDto createLanguageDto(String originalIndex, String name) {
        LanguageDto languageDto = new LanguageDto();
        languageDto.setName(name);
        languageDto.setOriginalIndex(originalIndex);

        return languageDto;
    }

    public static Level createLevel(String originalIndex, int level) {
        Level levelEntity = new Level();
        levelEntity.setLevel(level);
        levelEntity.setOriginalIndex(originalIndex);

        return levelEntity;
    }

    public static LevelDto createLevelDto(String originalIndex, int level) {
        LevelDto levelDto = new LevelDto();
        levelDto.setLevel(level);
        levelDto.setOriginalIndex(originalIndex);

        return levelDto;
    }

    public static MagicItem createMagicItem(String originalIndex, String name) {
        MagicItem magicItem = new MagicItem();
        magicItem.setName(name);
        magicItem.setOriginalIndex(originalIndex);

        return magicItem;
    }

    public static MagicItemDto createMagicItemDto(String originalIndex, String name) {
        MagicItemDto magicItemDto = new MagicItemDto();
        magicItemDto.setName(name);
        magicItemDto.setOriginalIndex(originalIndex);

        return magicItemDto;
    }

    public static MagicSchool createMagicSchool(String originalIndex, String name) {
        MagicSchool magicSchool = new MagicSchool();
        magicSchool.setName(name);
        magicSchool.setOriginalIndex(originalIndex);

        return magicSchool;
    }

    public static MagicSchoolDto createMagicSchoolDto(String originalIndex, String name) {
        MagicSchoolDto magicSchoolDto = new MagicSchoolDto();
        magicSchoolDto.setName(name);
        magicSchoolDto.setOriginalIndex(originalIndex);

        return magicSchoolDto;
    }

    public static Monster createMonster(String originalIndex, String name) {
        Monster monster = new Monster();
        monster.setName(name);
        monster.setOriginalIndex(originalIndex);

        return monster;
    }

    public static MonsterDto createMonsterDto(String originalIndex, String name) {
        MonsterDto monster = new MonsterDto();
        monster.setName(name);
        monster.setOriginalIndex(originalIndex);

        return monster;
    }

    public static Proficiency createProficiency(String originalIndex, String name) {
        Proficiency proficiency = new Proficiency();
        proficiency.setName(name);
        proficiency.setOriginalIndex(originalIndex);

        return proficiency;
    }

    public static ProficiencyDto createProficiencyDto(String originalIndex, String name) {
        ProficiencyDto proficiencyDto = new ProficiencyDto();
        proficiencyDto.setName(name);
        proficiencyDto.setOriginalIndex(originalIndex);

        return proficiencyDto;
    }

    public static Race createRace(String originalIndex, String name) {
        Race race = new Race();
        race.setName(name);
        race.setOriginalIndex(originalIndex);

        return race;
    }

    public static RaceDto createRaceDto(String originalIndex, String name) {
        RaceDto raceDto = new RaceDto();
        raceDto.setName(name);
        raceDto.setOriginalIndex(originalIndex);

        return raceDto;
    }

    public static Rule createRule(String originalIndex, String name) {
        Rule rule = new Rule();
        rule.setName(name);
        rule.setOriginalIndex(originalIndex);

        return rule;
    }

    public static RuleDto createRuleDto(String originalIndex, String name) {
        RuleDto ruleDto = new RuleDto();
        ruleDto.setName(name);
        ruleDto.setOriginalIndex(originalIndex);

        return ruleDto;
    }

    public static RuleSection createRuleSection(String originalIndex, String name) {
        RuleSection ruleSection = new RuleSection();
        ruleSection.setName(name);
        ruleSection.setOriginalIndex(originalIndex);

        return ruleSection;
    }

    public static RuleSectionDto createRuleSectionDto(String originalIndex, String name) {
        RuleSectionDto ruleSectionDto = new RuleSectionDto();
        ruleSectionDto.setName(name);
        ruleSectionDto.setOriginalIndex(originalIndex);

        return ruleSectionDto;
    }

    public static Skill createSkill(String originalIndex, String name) {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setOriginalIndex(originalIndex);

        return skill;
    }

    public static SkillDto createSkillDto(String originalIndex, String name) {
        SkillDto skillDto = new SkillDto();
        skillDto.setName(name);
        skillDto.setOriginalIndex(originalIndex);

        return skillDto;
    }

    public static Spell createSpell(String originalIndex, String name) {
        Spell spell = new Spell();
        spell.setName(name);
        spell.setOriginalIndex(originalIndex);

        return spell;
    }

    public static SpellDto createSpellDto(String originalIndex, String name) {
        SpellDto spellDto = new SpellDto();
        spellDto.setName(name);
        spellDto.setOriginalIndex(originalIndex);

        return spellDto;
    }

    public static Subclass createSubclass(String originalIndex, String name) {
        Subclass subclass = new Subclass();
        subclass.setName(name);
        subclass.setOriginalIndex(originalIndex);

        return subclass;
    }

    public static SubclassDto createSubclassDto(String originalIndex, String name) {
        SubclassDto subclassDto = new SubclassDto();
        subclassDto.setName(name);
        subclassDto.setOriginalIndex(originalIndex);

        return subclassDto;
    }

    public static Subrace createSubrace(String originalIndex, String name) {
        Subrace subrace = new Subrace();
        subrace.setName(name);
        subrace.setOriginalIndex(originalIndex);

        return subrace;
    }

    public static SubraceDto createSubraceDto(String originalIndex, String name) {
        SubraceDto subraceDto = new SubraceDto();
        subraceDto.setName(name);
        subraceDto.setOriginalIndex(originalIndex);

        return subraceDto;
    }

    public static Trait createTrait(String originalIndex, String name) {
        Trait trait = new Trait();
        trait.setName(name);
        trait.setOriginalIndex(originalIndex);

        return trait;
    }

    public static TraitDto createTraitDto(String originalIndex, String name) {
        TraitDto traitDto = new TraitDto();
        traitDto.setName(name);
        traitDto.setOriginalIndex(originalIndex);

        return traitDto;
    }

    public static WeaponProperty createWeaponProperty(String originalIndex, String name) {
        WeaponProperty weaponProperty = new WeaponProperty();
        weaponProperty.setName(name);
        weaponProperty.setOriginalIndex(originalIndex);

        return weaponProperty;
    }

    public static WeaponPropertyDto createWeaponPropertyDto(String originalIndex, String name) {
        WeaponPropertyDto weaponPropertyDto = new WeaponPropertyDto();
        weaponPropertyDto.setName(name);
        weaponPropertyDto.setOriginalIndex(originalIndex);

        return weaponPropertyDto;
    }
}
