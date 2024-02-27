package com.protas.movieapp.controller.director;

import com.protas.movieapp.dto.DirectorDTO;
import com.protas.movieapp.entity.movie.Director;
import com.protas.movieapp.repository.DirectorRepository;
import com.protas.movieapp.service.director.DirectorCreateService;
import com.protas.movieapp.service.director.DirectorDeleteService;
import com.protas.movieapp.service.director.DirectorReadService;
import com.protas.movieapp.service.director.DirectorUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/director")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorCreateService directorCreateService;
    private final DirectorReadService directorReadService;
    private final DirectorUpdateService directorUpdateService;
    private final DirectorDeleteService directorDeleteService;

    @PostMapping
    public ResponseEntity<DirectorDTO> create(@RequestBody @Valid DirectorDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(directorCreateService.create(dto));
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

    @PutMapping("/{id}")
    public ResponseEntity<Director> update(@PathVariable Long id, @RequestBody DirectorDTO dto) {
        return ResponseEntity.ok(directorUpdateService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
