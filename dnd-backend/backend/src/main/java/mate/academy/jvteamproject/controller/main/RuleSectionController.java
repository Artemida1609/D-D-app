package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.rule.RuleSectionDto;
import mate.academy.jvteamproject.service.main.RuleSectionService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rule section", description = "Rule section controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rule-sections")
public class RuleSectionController {
    private final RuleSectionService ruleSectionService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get rule section by originalIndex")
    public RuleSectionDto getByIndex(@PathVariable String index) {
        return ruleSectionService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of rule section")
    public Page<RuleSectionDto> getAll(@ParameterObject
                                       @PageableDefault(sort = "name",
                                            direction = Sort.Direction.ASC)Pageable pageable) {
        return ruleSectionService.getAll(pageable);
    }
}
