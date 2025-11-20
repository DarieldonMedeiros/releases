package releases.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import releases.model.entity.Release;
import releases.model.repository.ReleaseRepository;
import releases.service.ReleaseService;
import releases.service.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j // camada intermediária de código e log
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    @Transactional
    public Release create(Release release, String userToken){
        log.info("Criando nova release {}", release.getSystem());
        release.setUserUpdate(userToken);
        return releaseRepository.save(release);
    }

    @Transactional // Commits é uma coleção, portanto, ele precisa ser transacional
    public Release findById(Long id){
        return releaseRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release não encontrada"));
    }

    @Transactional
    public void updateNotes(Long id, String notes, String userToken){
        Release release = findById(id);
        release.setNotes(notes);
        release.setUserUpdate(userToken);
        releaseRepository.save(release);
    }

    @Transactional
    public void softDelete(Long id){
        Release release = findById(id);
        release.softDelete();
        releaseRepository.save(release);
    }
}
