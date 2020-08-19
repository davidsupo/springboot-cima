package pe.edu.colegiocima.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.colegiocima.demo.models.entities.GradoSeccion;
import pe.edu.colegiocima.demo.repository.GradoSeccionRepository;

@Service
public class GradoSeccionServiceImpl implements GradoSeccionService{

    @Autowired
    private GradoSeccionRepository repository;

    @Override
    public Iterable<GradoSeccion> findAll() {
        return repository.findAll();
    }

    @Override
    public GradoSeccion findById(Short id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public GradoSeccion save(GradoSeccion gradoSeccion) {
        return repository.save(gradoSeccion);
    }
}
