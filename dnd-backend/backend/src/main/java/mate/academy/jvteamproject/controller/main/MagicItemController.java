package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.magic.MagicItemDto;
import mate.academy.jvteamproject.service.main.MagicItemService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Magic item", description = "Magic item controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/magic-items")
public class MagicItemController {
    private final MagicItemService magicItemService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get magic item by originalIndex")
    public MagicItemDto getByIndex(@PathVariable String index) {
        return magicItemService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of magic items")
    public Page<MagicItemDto> getAll(@ParameterObject
                                     @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return magicItemService.getAll(pageable);
    }
}
