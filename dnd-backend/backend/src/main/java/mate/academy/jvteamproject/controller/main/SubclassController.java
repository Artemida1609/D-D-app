package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.subclass.SubclassDto;
import mate.academy.jvteamproject.service.main.SubclassService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Subclass", description = "Subclass controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subclasses")
public class SubclassController {
    private final SubclassService subclassService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get subclass by originalIndex")
    public SubclassDto getByIndex(@PathVariable String index) {
        return subclassService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of subclass")
    public Page<SubclassDto> getAll(@ParameterObject
                                    @PageableDefault(sort = "name",
                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return subclassService.getAll(pageable);
    }
}
