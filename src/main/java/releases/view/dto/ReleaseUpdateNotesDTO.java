package releases.view.dto;

import jakarta.validation.constraints.NotBlank;

// Utilizado na chamada PUT
public record ReleaseUpdateNotesDTO(
        @NotBlank(message = "O campo 'notes' é obrigatório")
        String notes
) {}
