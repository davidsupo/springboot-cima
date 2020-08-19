package pe.edu.colegiocima.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pe.edu.colegiocima.demo.models.entities.AlumnoColegio;

// CrudRepository < Entidad, TipoDeDatoDelID >

@Repository
public interface AlumnoColegioRepository extends JpaRepository<AlumnoColegio,Long> {
    public Iterable<AlumnoColegio> findByEstadoTrueAndIdAnioLectivo(Short idAnioLectivo);

    @Query("select ac from AlumnoColegio ac where ac.idAnioLectivo = ?1 and ac.gradoSeccion.id=?2")
    public Iterable<AlumnoColegio> listarAlumnos(Short id1, Short id2);

    public Page<AlumnoColegio> findAll(Pageable page);

}
