package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.classes.ClassDto;
import mate.academy.jvteamproject.dto.level.LevelDto;
import mate.academy.jvteamproject.service.main.ClassService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Class", description = "Class controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/classes")
public class ClassController {
    private final ClassService classService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get class by originalIndex")
    public ClassDto getByIndex(@PathVariable String index) {
        return classService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of class")
    public Page<ClassDto> getAll(@ParameterObject
                                 @PageableDefault(sort = "name",
                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return classService.getAll(pageable);
    }

    @GetMapping("/{index}/level/{level}")
    @Operation(summary = "get level", description = "get level by class originalIndex and level")
    public LevelDto getLevelByIdAndLevel(@PathVariable String index, @PathVariable int level) {
        return classService.getLevelByOriginalIndexAndLevel(index, level);
    }

    @GetMapping("/{index}/level")
    @Operation(summary = "get all Levels", description = "get all levels by class originalIndex")
    public Page<LevelDto> getAllLevelsById(
            @PathVariable String index,
            @ParameterObject
            @PageableDefault(sort = "originalIndex",
                             direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return classService.getAllLevelsByIndex(index, pageable);
    }
}
