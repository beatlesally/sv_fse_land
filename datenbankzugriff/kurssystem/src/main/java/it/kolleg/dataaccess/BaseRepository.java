package it.kolleg.dataaccess;

import java.util.List;
import java.util.Optional;

/**
 * Gibt Standard CRUD Methoden vor; unabhängig von Entity
 */
public interface BaseRepository<T,I> { //parametrisiert! bei implements festlegen

    Optional<T> insert(T entity); //wenn methode erfolgreich wird optional ersetzt mit richtigen returnwert onst optional zurückgegeben
    Optional<T> getById(I id); //über ID entity herausbekommen
    List<T> getAll();
    Optional<T> update(T entity);
    void deleteById(I id);

}
