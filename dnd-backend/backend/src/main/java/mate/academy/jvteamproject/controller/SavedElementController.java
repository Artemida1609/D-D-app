package mate.academy.jvteamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.jvteamproject.dto.saved.SavedElementRequestDto;
import mate.academy.jvteamproject.dto.saved.SavedElementResponseDto;
import mate.academy.jvteamproject.service.SavedElementService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "saved an element", description = "saved an element")
@RestController
@RequestMapping("/saved")
@RequiredArgsConstructor
public class SavedElementController {

    private final SavedElementService savedItemService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add")
    @Operation(summary = "add", description = "add an element")
    @ResponseStatus(HttpStatus.CREATED)
    public SavedElementResponseDto add(@RequestBody @Valid SavedElementRequestDto requestDto) {
        return savedItemService.saveElement(requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    @Operation(summary = "delete", description = "delete an element")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        savedItemService.removeElement(id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "get all", description = "get all elements")
    public List<SavedElementResponseDto> getAll() {
        return savedItemService.getAllSavedElementsByUser();
    }
}
