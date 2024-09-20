package tr.com.fazil.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import tr.com.fazil.configuration.redis.RedisService;
import tr.com.fazil.data.dto.RedisKullaniciDto;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final RedisService redisService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            HandlerExceptionResolver handlerExceptionResolver,
            RedisService redisService) {
        this.jwtService = jwtService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.redisService = redisService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final var claims = jwtService.extractClaims(jwt);

            var uuid = claims.get("uuid");
            var subUuidFromToken = claims.get("subUuid");

            var subUuidFromRedis = (RedisKullaniciDto) redisService.getValue((String) uuid);

            if (subUuidFromToken.equals(subUuidFromRedis.getSubUuid())) {
                System.out.println("Eşleşme yapıldı.");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        subUuidFromRedis.getKullaniciDto(), subUuidFromRedis.getKullaniciDto().getId(),
                        subUuidFromRedis.getYetkiList());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Token eşleşmeme hatası
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}
