package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.proficiency.ProficiencyDto;
import mate.academy.jvteamproject.service.main.ProficiencyService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Proficiency", description = "Proficiency controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/proficiencies")
public class ProficiencyController {
    private final ProficiencyService proficiencyService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get proficiency by originalIndex")
    public ProficiencyDto getByIndex(@PathVariable String index) {
        return proficiencyService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of proficiency")
    public Page<ProficiencyDto> getAll(@ParameterObject
                                       @PageableDefault(sort = "name",
                                            direction = Sort.Direction.ASC) Pageable pageable) {
        return proficiencyService.getAll(pageable);
    }
}
