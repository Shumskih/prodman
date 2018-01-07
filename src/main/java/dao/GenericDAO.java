package dao;

import java.util.List;
import java.util.UUID;

public interface GenericDAO<T, ID> {
    void save(T t);

    List<T> getAll();

    void update(T t);

    void delete(UUID id);
}
