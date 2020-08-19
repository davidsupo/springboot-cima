package pe.edu.colegiocima.demo.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gradoseccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradoSeccion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "seccion")
    @Size(max = 6, message = "La sección debe contener como máximo 6 caracteres")
    @NotNull
    @NotEmpty
    private String seccion;

    @Column(name = "vacante")
    private Long vacante;

    @Column(name = "idannolectivo")
    @NotNull
    private Short idAnioLectivo;

    @Column(name = "idgrado")
    @NotNull
    private Short idGrado;

    @Column(name = "idaula")
    @NotNull
    private Short idAula;

    @Column(name = "idsede")
    @NotNull
    private Short idSede;

    @Column(name = "idgradosecciongrupoestudio")
    private Long idgradosecciongrupoestudio;

}
