package pe.edu.colegiocima.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pe.edu.colegiocima.demo.models.entities.Usuario;
import pe.edu.colegiocima.demo.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = repository.findByLogin(login)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no se encuentra con el login " + login));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new User(usuario.getLogin(),new BCryptPasswordEncoder().encode(usuario.getPass()),authorities);
    }


}
