package pe.edu.colegiocima.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alumnocolegio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoColegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "codigoeducando")
    private String codigoEducando;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "fechamatricula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMatricula;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "observaciongerencia")
    private String observacionGerencia;

    @Column(name = "idpersona")
    private Long idPersona;

    @Column(name = "idturno")
    private Short idTurno;

    @Column(name = "idannolectivo")
    private Short idAnioLectivo;

    @Column(name = "idsituacion")
    private Short idSituacion;

    @Column(name = "idcondicion")
    private Short idCondicion;

    @JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "idgradoseccion")
    private GradoSeccion gradoSeccion;

    @Column(name = "idusuario")
    private Short idUsuario;

    @Column(name = "idespecialidad")
    private Short idEspecialidad;

    @Column(name = "idpostulantecolegio")
    private Long idPostulanteColegio;

    @JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer"})
    @OneToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "idgradoseccioninicio")
    private GradoSeccion gradoSeccionInicio;

    @Column(name = "ratifica")
    private Boolean ratifica;

    @Column(name = "idusuariocobranza")
    private Long idUsuarioCobranza;

    @Column(name = "idcirculoestudio")
    private Long idCirculoEstudio;

    @Column(name = "idsederatifica")
    private Long idSedeRatifica;

    @Column(name = "estadoeconomico")
    private Short estadoEconomico;

    @Column(name = "fecharetiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRetiro;

}
