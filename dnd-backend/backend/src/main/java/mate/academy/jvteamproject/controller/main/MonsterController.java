package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.monster.MonsterDto;
import mate.academy.jvteamproject.service.main.MonsterService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Monster", description = "Monster controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monsters")
public class MonsterController {
    private final MonsterService monsterService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get monster by originalIndex")
    public MonsterDto getByIndex(@PathVariable String index) {
        return monsterService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of monster")
    public Page<MonsterDto> getAll(@ParameterObject
                                   @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return monsterService.getAll(pageable);
    }
}
