package pe.edu.colegiocima.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.colegiocima.demo.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Short> {
    public Optional<Usuario> findByLogin(String login);
}
