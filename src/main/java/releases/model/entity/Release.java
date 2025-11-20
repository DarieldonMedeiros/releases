package releases.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "release")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo 'system' é obrigatório")
    @Size(max = 255, message = "O campo 'system' deve ter no máximo 255 caracteres")
    @Column(nullable = false) //length padrão é 255
    private String system;

    @NotBlank(message = "O campo 'version' é obrigatório")
    @Size(max = 30, message = "O campo 'version' deve ter no máximo 30 caracteres")
    @Column(nullable = false, length = 30)
    private String version;

    @ElementCollection
    @CollectionTable(name = "release_commits", joinColumns = @JoinColumn(name = "release_id"))
    @Column(name = "commit")
    private List<String> commits;

    // Large Object
    @Lob
    private String notes;

    @NotBlank(message = "O campo 'user' é obrigatório")
    @Size(max = 100, message = "O campo 'user' deve ter no máximo 100 caracteres")
    @Column(name= "release_user",nullable = false, length = 100)
    private String user;

    @Column(length = 100)
    private String userUpdate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime releasedAt;

    private LocalDateTime deletedAt;

    // Trim automático na variável system
    public void setSystem(String system){
        this.system = (system != null) ? system.trim() : null;
    }

    // Soft Delete
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public boolean isDeleted(){
        return this.deletedAt != null;
    }

    // Equals e Hashcode para o ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release release = (Release) o;
        return Objects.equals(getId(), release.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
