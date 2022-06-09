package ufv.dis.final2022.JMI;

public class ObjetoBuscar {

    private String entity;
    private String id;

    public ObjetoBuscar() {
    }

    public ObjetoBuscar(String entity, String id) {
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ObjetoBuscar{" +
                "entity='" + entity + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
