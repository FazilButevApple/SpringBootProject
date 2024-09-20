package tr.com.fazil.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity //Spring Security'deki yetkilendirme işlemlerini metod seviyesinde yapabilmeni sağlar.
                    // Bu, belirli servis veya kontrolör metodlarına kimin erişebileceğini belirlemek için kullanılır.
@EnableWebSecurity //Spring Security'yi projeye entegre eden anotasyondur. Güvenlik yapılandırmasını devreye alır.
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter; // Gelen isteklerin içindeki JWT'yi doğrulayacak ve geçerli olup olmadığını kontrol edecek.

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF (Cross-Site Request Forgery) koruması devre dışı bırakılıyor.
                // Çünkü JWT kullanıldığında genellikle CSRF korumasına ihtiyaç duyulmaz, zira JWT her istekle gönderilir ve sunucu tarafında stateless (durumsuz) bir yapı kullanılır.
                .authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers("/auth/**").permitAll(); // Sadece /auth/** url ine izin var
                            authorize.anyRequest().authenticated(); // Diğer url lere izin yok
                        }
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //  Gerçek zamanlı oyunlar, anlık mesajlaşma uygulamaları, canlı yayın uygulamaları. mantıklı değil
                        //Oturum yönetimi stateless (durumsuz) olacak şekilde ayarlanmıştır. Bu, sunucu tarafında herhangi bir oturum (session) tutulmayacağı anlamına gelir.
                        // JWT ile çalışırken, oturum tutulmadığı için bu yapı önemlidir. Her istekle birlikte JWT token'ı gönderilir.
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                // jwtAuthenticationFilter'i UsernamePasswordAuthenticationFilter filtresinden önce ekler.
                // Bu, JWT doğrulama işleminin, standart kullanıcı adı/şifre doğrulama işlemi yapılmadan önce gerçekleşmesini sağlar.
        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
