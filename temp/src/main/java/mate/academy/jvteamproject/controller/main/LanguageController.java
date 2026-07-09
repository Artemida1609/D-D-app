package mate.academy.jvteamproject.controller.main;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.language.LanguageDto;
import mate.academy.jvteamproject.service.main.LanguageService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Language", description = "Language controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/languages")
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping("/{index}")
    @Operation(summary = "get element", description = "get language by originalIndex")
    public LanguageDto getByIndex(@PathVariable String index) {
        return languageService.getByOriginalIndex(index);
    }

    @GetMapping
    @Operation(summary = "get all", description = "get all elements of language")
    public Page<LanguageDto> getAll(@ParameterObject
                                    @PageableDefault(sort = "name",
                                        direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return languageService.getAll(pageable);
    }
}
