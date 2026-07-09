package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.trait.TraitDto;
import mate.academy.jvteamproject.service.main.TraitService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Trait", description = "Trait controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/traits")
public class TraitController {
    private final TraitService traitService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get trait by originalIndex")
    public TraitDto getByIndex(@PathVariable String index) {
        return traitService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of trait")
    public Page<TraitDto> getAll(@ParameterObject
                                 @PageableDefault(sort = "name",
                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return traitService.getAll(pageable);
    }
}
