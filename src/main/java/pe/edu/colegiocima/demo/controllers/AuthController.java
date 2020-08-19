package pe.edu.colegiocima.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.colegiocima.demo.models.jwt.JwtRefreshToken;
import pe.edu.colegiocima.demo.models.jwt.JwtRequest;
import pe.edu.colegiocima.demo.models.jwt.JwtResponse;
import pe.edu.colegiocima.demo.security.JwtToken;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/login")
@RestController
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
    public ResponseEntity<?> autenticar(@RequestBody JwtRequest jwtRequest) throws Exception{

        Map<String,Object> respuesta = new HashMap<>();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (DisabledException e){
            respuesta.put("mensaje","Usuario está deshabilitado");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }catch (BadCredentialsException e){
            respuesta.put("mensaje","El usuario o contraseña es incorrecto");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(respuesta);
        }
    }


    @PostMapping("/refrescar")
    public ResponseEntity<?> refrescarToken(@RequestBody JwtRefreshToken jwtRefresToken){
        try {
            String token = jwtTokenUtil.regenerateToken(jwtRefresToken.getJwtToken());
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
