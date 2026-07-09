package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.ability.AbilityScoreDto;
import mate.academy.jvteamproject.service.main.AbilityScoreService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Ability score", description = "Ability score controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ability-scores")
public class AbilityScoreController {
    private final AbilityScoreService abilityScoreService;

    @GetMapping("/{index}")
    @Operation(summary = "get ability score", description = "get ability score by originalIndex")
    public AbilityScoreDto getByIndex(@PathVariable String index) {
        return abilityScoreService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of ability score")
    public Page<AbilityScoreDto> getAll(@ParameterObject
                                        @PageableDefault(sort = "name",
                                            direction = Sort.Direction.ASC) Pageable pageable) {
        return abilityScoreService.getAll(pageable);
    }
}
