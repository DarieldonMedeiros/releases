package releases.service;

public interface CrudService<ID, T> {
    T create(T entity, String userToken);
    T findById(ID id);
    void updateNotes(ID id, String notes, String userToken);
    void softDelete(ID id);
}
