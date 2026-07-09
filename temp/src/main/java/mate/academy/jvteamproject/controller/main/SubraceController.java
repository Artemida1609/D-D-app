package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.subrace.SubraceDto;
import mate.academy.jvteamproject.service.main.SubraceService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Subrace", description = "Subrace controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subraces")
public class SubraceController {
    private final SubraceService subraceService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get subrace by originalIndex")
    public SubraceDto getByIndex(@PathVariable String index) {
        return subraceService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of subrace")
    public Page<SubraceDto> getAll(@ParameterObject
                                   @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return subraceService.getAll(pageable);
    }
}
