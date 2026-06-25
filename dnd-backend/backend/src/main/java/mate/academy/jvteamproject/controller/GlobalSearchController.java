package mate.academy.jvteamproject.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.api.SearchResult;
import mate.academy.jvteamproject.service.GlobalSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class GlobalSearchController {

    private final GlobalSearchService searchService;

    @GetMapping
    public List<SearchResult> search(@RequestParam String name) {
        return searchService.search(name);
    }
}
