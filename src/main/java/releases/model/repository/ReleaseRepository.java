package releases.model.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import releases.model.entity.Release;

import java.util.List;
import java.util.Optional;

// Colocar somente consultas (caso necessário)
@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    @EntityGraph(attributePaths = "commits") // carrega os commits automaticamente
    Optional<Release> findByIdAndDeletedAtIsNull(Long id);
    List<Release> findAllByDeletedAtIsNull();

}
