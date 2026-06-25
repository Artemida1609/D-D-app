package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.equipment.EquipmentDto;
import mate.academy.jvteamproject.service.main.EquipmentService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Equipment", description = "Equipment controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipments")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get equipment by originalIndex")
    public EquipmentDto getByIndex(@PathVariable String index) {
        return equipmentService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of equipment")
    public Page<EquipmentDto> getAll(@ParameterObject
                                     @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return equipmentService.getAll(pageable);
    }
}
