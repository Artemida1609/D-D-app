package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.feature.FeatureDto;
import mate.academy.jvteamproject.service.main.FeatureService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Feature", description = "Feature controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/features")
public class FeatureController {
    private final FeatureService featureService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get feature by originalIndex")
    public FeatureDto getByIndex(@PathVariable String index) {
        return featureService.getById(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of feature")
    public Page<FeatureDto> getAll(@ParameterObject
                                   @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable) {
        return featureService.getAll(pageable);
    }
}
