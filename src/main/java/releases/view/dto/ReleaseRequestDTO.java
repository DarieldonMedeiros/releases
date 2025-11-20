package releases.view.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

//Utilizado na chamada POST
public record ReleaseRequestDTO(
        @NotBlank(message = "O campo 'system' é obrigatório")
        @Size(max = 255, message = "O campo 'system' deve ter no máximo 255 caracteres")
        String system,

        @NotBlank(message = "O campo 'version' é obrigatório")
        @Size(max = 30, message = "O campo 'version' deve ter no máximo 30 caracteres")
        String version,

        List<String> commits,

        String notes,

        @NotBlank(message = "O campo 'user' é obrigatório")
        @Size(max = 100, message = "O campo 'user' deve ter no máximo 100 caracteres")
        String user
) {}
