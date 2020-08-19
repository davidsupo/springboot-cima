package pe.edu.colegiocima.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.colegiocima.demo.models.entities.AlumnoColegio;
import pe.edu.colegiocima.demo.repository.AlumnoColegioRepository;

@Service
public class AlumnoColegioServiceImpl implements AlumnoColegioService{

    @Autowired
    private AlumnoColegioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<AlumnoColegio> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoColegio buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AlumnoColegio guardar(AlumnoColegio alumnoColegio) {
        return repository.save(alumnoColegio);
    }

    @Override
    public Iterable<AlumnoColegio> listarActivoPorAnioLectivo(Short idAnioLectivo) {
        return repository.findByEstadoTrueAndIdAnioLectivo(idAnioLectivo);
    }

    @Override
    public Iterable<AlumnoColegio> listarAlumnos(Short idAnioLectivo, Short idGradoSeccion) {
        return repository.listarAlumnos(idAnioLectivo,idGradoSeccion);
    }

    @Override
    public Page<AlumnoColegio> paginar(Pageable page) {
        return repository.findAll(page);
    }
}
