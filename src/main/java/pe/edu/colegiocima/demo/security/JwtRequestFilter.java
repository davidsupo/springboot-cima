package pe.edu.colegiocima.demo.security;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // Capturar el token.
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // Validar que el token distinto de null y que este correctamente formado
        // Bearer HASH
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            // Obtener el token
            token = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(token);
            }catch (IllegalArgumentException e){
                log.error("No se puede obtener el token JWT");
            }catch (ExpiredJwtException e){
                log.error("Token JWT expirado");
            }
        }else if(requestTokenHeader == null){
            log.error("No se proporciono el token JWT");
        }else if(!requestTokenHeader.startsWith("Bearer ")){
            log.error("TOKEN JWT no comienza con Bearer");
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request,response);

    }
}
