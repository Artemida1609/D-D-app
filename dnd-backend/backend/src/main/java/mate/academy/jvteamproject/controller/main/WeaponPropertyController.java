package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.weapon.WeaponPropertyDto;
import mate.academy.jvteamproject.service.main.WeaponPropertyService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Weapon property", description = "Weapon property controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weapon-properties")
public class WeaponPropertyController {
    private final WeaponPropertyService weaponPropertyService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get weapon property by originalIndex")
    public WeaponPropertyDto getByIndex(@PathVariable String index) {
        return weaponPropertyService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of weapon property")
    public Page<WeaponPropertyDto> getAll(@ParameterObject
                                          @PageableDefault(sort = "name",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
        return weaponPropertyService.getAll(pageable);
    }
}
