package com.protas.movieapp.controller.director;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import com.protas.movieapp.service.director.DirectorReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorReadService directorReadService;
    private final DirectorRepository directorRepository;

    @PostMapping
    public ResponseEntity<Director> create(DirectorDTO dto) {
        Director director = new Director();
        director.setFirstName(dto.firstName());
        director.setLastName(dto.lastName());
        return ResponseEntity.ok(directorRepository.save(director));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> findById(@PathVariable Long id) {
        return ResponseEntity.ok(directorReadService.findDirectorById(id));
    }

    @GetMapping
    public ResponseEntity<List<Director>> findAll(@PageableDefault(value = Integer.MAX_VALUE,
            sort = "DESC") Pageable pageable) {
        return ResponseEntity.ok(directorReadService.findAll(pageable));
    }
}
