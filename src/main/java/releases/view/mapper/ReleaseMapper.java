package releases.view.mapper;

import releases.model.entity.Release;
import releases.view.dto.ReleaseCreateResponseDTO;
import releases.view.dto.ReleaseRequestDTO;
import releases.view.dto.ReleaseResponseDTO;

public class ReleaseMapper {

    // Transforma ReleaseRequestDTO em entidade(Release)
    public static Release toEntity(ReleaseRequestDTO dto){
        return Release.builder()
                .system(dto.system())
                .version(dto.version())
                .commits(dto.commits())
                .notes(dto.notes())
                .user(dto.user())
                .build();
    }

    // Transforma entidade(Release) em ReleaseResponseDTO
    public static ReleaseResponseDTO toResponseDTO(Release entity){
        return new ReleaseResponseDTO(
                "Release listado com sucesso",
                entity.getId(),
                entity.getSystem(),
                entity.getVersion(),
                entity.getCommits(),
                entity.getNotes(),
                entity.getUser(),
                entity.getUserUpdate(),
                entity.getReleasedAt()
        );
    }
    // Transforma entidade(Release) em ReleaseCreateResponseDTO
    public static ReleaseCreateResponseDTO toCreateResponseDTO(Release entity){
        return new ReleaseCreateResponseDTO(
                entity.getId(),
                "Release listado com sucesso"
        );
    }
}
