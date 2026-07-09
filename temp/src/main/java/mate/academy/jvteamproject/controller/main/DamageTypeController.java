package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.damage.DamageTypeDto;
import mate.academy.jvteamproject.service.main.DamageTypeService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Damage type", description = "Damage type controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/damage-types")
public class DamageTypeController {
    private final DamageTypeService damageTypeService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get damage type by originalIndex")
    public DamageTypeDto getByIndex(@PathVariable String index) {
        return damageTypeService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of damage type")
    public Page<DamageTypeDto> getAll(@ParameterObject
                                      @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return damageTypeService.getAll(pageable);
    }
}
