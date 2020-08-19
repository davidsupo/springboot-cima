package pe.edu.colegiocima.demo.services;

import pe.edu.colegiocima.demo.models.entities.GradoSeccion;

public interface GradoSeccionService {
    public Iterable<GradoSeccion> findAll();
    public GradoSeccion findById(Short id);
    public GradoSeccion save(GradoSeccion gradoSeccion);
}
