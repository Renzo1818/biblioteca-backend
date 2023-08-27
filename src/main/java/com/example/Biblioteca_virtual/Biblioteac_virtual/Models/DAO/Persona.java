package com.example.Biblioteca_virtual.Biblioteac_virtual.Models.DAO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "PERSONA", uniqueConstraints = {
        @UniqueConstraint(name="UK_DNI", columnNames = "DNI"),
        @UniqueConstraint(name="UK_TELEFONO", columnNames = "TELEFONO")
})
@ToString
public class Persona {
    @Id
    @Column(name = "ID_PERSONA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id_persona;

    @Column(name = "NOMBRE", length = 40)
    @Getter @Setter
    private String nombre;

    @Column(name = "APELLIDO", length = 40)
    @Getter @Setter
    private String apellido;

    @Column(name = "DNI", length = 8)
    @Getter @Setter
    private String dni;

    @Column(name = "TELEFONO", length = 9)
    @Getter @Setter
    private String telefono;

    @ManyToOne(targetEntity = TipoPersona.class)
    @JoinColumn(name = "ID_TIPO_PERSONA", foreignKey = @ForeignKey(name = "FK_ID_TIPO_PERSONA"))
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter @Setter
    private TipoPersona tipo;

    @ManyToOne(targetEntity = Distrito.class)
    @JoinColumn(name ="ID_DISTRITO", foreignKey = @ForeignKey(name="FK_ID_DISTRITO"))
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter @Setter
    private Distrito distrito;

    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @Getter @Setter
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(targetEntity = Inventario.class, mappedBy = "persona", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<Inventario> inventarios;

    @JsonIgnore
    @OneToMany(targetEntity = ReservaVirtual.class, mappedBy = "persona", cascade = CascadeType.ALL)
    @Getter @Setter
    private List<ReservaVirtual> reservaVirtuals;


    public Persona() {
    }

}
