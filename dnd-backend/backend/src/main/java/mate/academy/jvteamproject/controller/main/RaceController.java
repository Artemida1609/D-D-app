package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.race.RaceDto;
import mate.academy.jvteamproject.service.main.RaceService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Race", description = "Race controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/races")
public class RaceController {
    private final RaceService raceService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get race by originalIndex")
    public RaceDto getByIndex(@PathVariable String index) {
        return raceService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of race")
    public Page<RaceDto> getAll(@ParameterObject
                                @PageableDefault(sort = "name",
                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return raceService.getAll(pageable);
    }
}
