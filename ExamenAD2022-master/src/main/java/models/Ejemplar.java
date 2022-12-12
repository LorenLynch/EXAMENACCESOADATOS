package models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Ejemplar implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    private String estado; /* excelente, bueno, regular, malo */
    private Integer edicion;
    @ManyToOne()
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public Ejemplar() {
    }

    public Ejemplar(String estado, Integer edicion) {
        this.estado = estado;
        this.edicion = edicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "id=" + id + ", estado=" + estado + ", edicion=" + edicion + '}';
    }
    
    
}
