package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.rule.RuleDto;
import mate.academy.jvteamproject.service.main.RuleService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rule", description = "Rule controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rules")
public class RuleController {
    private final RuleService ruleService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get rule by originalIndex")
    public RuleDto getByIndex(@PathVariable String index) {
        return ruleService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of rule")
    public Page<RuleDto> getAll(@ParameterObject
                                @PageableDefault(sort = "name",
                                    direction = Sort.Direction.ASC) Pageable pageable) {
        return ruleService.getAll(pageable);
    }
}
