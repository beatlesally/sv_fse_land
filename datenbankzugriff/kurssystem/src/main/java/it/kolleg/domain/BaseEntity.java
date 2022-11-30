package it.kolleg.domain;

/**
 * Basisklasse für alle Entities, die wird später definieren möchten.
 */
public abstract class BaseEntity {
    private Long id;

    public BaseEntity(Long id)
    {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id)
    {
        if(id==null || id >= 0) //wenn id null -> insert! weil A_I || wenn id >= 0 Eintrag in DB vorhanden -> update!
        {
            this.id = id;
        } else {
            throw new InvalidValueException("Kurs-ID muss größer gleich 0 sein!");
        }
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
