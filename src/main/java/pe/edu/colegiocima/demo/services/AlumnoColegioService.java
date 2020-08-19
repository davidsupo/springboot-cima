package pe.edu.colegiocima.demo.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.colegiocima.demo.models.entities.AlumnoColegio;

public interface AlumnoColegioService {
    public Iterable<AlumnoColegio> findAll();
    public AlumnoColegio buscarPorId(Long id);
    public AlumnoColegio guardar(AlumnoColegio alumnoColegio);
    public Iterable<AlumnoColegio> listarActivoPorAnioLectivo(Short idAnioLectivo);
    public Iterable<AlumnoColegio> listarAlumnos(Short idAnioLectivo, Short idGradoSeccion);
    public Page<AlumnoColegio> paginar(Pageable page);
}
