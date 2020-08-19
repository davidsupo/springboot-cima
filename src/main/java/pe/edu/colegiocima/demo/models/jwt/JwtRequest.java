package pe.edu.colegiocima.demo.models.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
