package releases.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import releases.model.entity.Release;
import releases.service.ReleaseService;
import releases.view.dto.*;
import releases.view.mapper.ReleaseMapper;

@RestController
@RequestMapping("/releases")
@RequiredArgsConstructor
public class ReleaseController {

    private final ReleaseService releaseService;

    @PostMapping
    public ResponseEntity<ReleaseCreateResponseDTO> create(@RequestBody @Valid ReleaseRequestDTO dto, @RequestHeader("Authorization") String token){
        Release release = releaseService.create(ReleaseMapper.toEntity(dto), extractUser(token));
        return ResponseEntity.ok(ReleaseMapper.toCreateResponseDTO(release));
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ReleaseResponseDTO> getById(@PathVariable Long id){
        Release release = releaseService.findById(id);
        return ResponseEntity.ok(ReleaseMapper.toResponseDTO(release));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDTO> updateNotes(@PathVariable Long id, @RequestBody ReleaseUpdateNotesDTO dto, @RequestHeader("Authorization") String token) {
        releaseService.updateNotes(id, dto.notes(), extractUser(token));
        return ResponseEntity.ok(new MessageDTO("Release atualizado com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDTO> softDelete(@PathVariable Long id){
        releaseService.softDelete(id);
        return ResponseEntity.ok(new MessageDTO("Release deletado com sucesso."));
    }

    // Função auxiliar com um simples mock
    private String extractUser(String token){
        return "mockedUser";
    }
}
