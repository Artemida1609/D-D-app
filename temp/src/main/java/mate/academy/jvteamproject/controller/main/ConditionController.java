package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.condition.ConditionDto;
import mate.academy.jvteamproject.service.main.ConditionService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Condition", description = "Condition controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conditions")
public class ConditionController {
    private final ConditionService conditionService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get condition by originalIndex")
    public ConditionDto getByIndex(@PathVariable String index) {
        return conditionService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of condition")
    public Page<ConditionDto> getAll(@ParameterObject
                                     @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return conditionService.getAll(pageable);
    }
}
