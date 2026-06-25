package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.school.MagicSchoolDto;
import mate.academy.jvteamproject.service.main.MagicSchoolService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Magic school", description = "Magic school controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/magic-schools")
public class MagicSchoolController {
    private final MagicSchoolService magicSchoolService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get magic school by originalIndex")
    public MagicSchoolDto getByIndex(@PathVariable String index) {
        return magicSchoolService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of magic school")
    public Page<MagicSchoolDto> getAll(@ParameterObject
                                       @PageableDefault(sort = "name",
                                            direction = Sort.Direction.ASC)Pageable pageable) {
        return magicSchoolService.getAll(pageable);
    }
}
