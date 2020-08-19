package pe.edu.colegiocima.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.colegiocima.demo.models.entities.GradoSeccion;

@Repository
public interface GradoSeccionRepository extends JpaRepository<GradoSeccion,Short> {
}
