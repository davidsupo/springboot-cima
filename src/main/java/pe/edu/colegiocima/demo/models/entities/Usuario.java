package pe.edu.colegiocima.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "dni")
    @NotNull
    @NotEmpty
    private String dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechanacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "sexo")
    private Boolean sexo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "telefonosms")
    private String telefonoSms;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email")
    private String email;

    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String pass;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "alias")
    private String alias;

    @Column(name = "sistema")
    private Boolean sistema;

    @Column(name = "planilla")
    private Boolean planilla;

    @Column(name = "correoinstitucional")
    private String correoInstitucional;

    @PrePersist
    public void prePersist(){
        this.fechaRegistro = new Date();
    }

}
