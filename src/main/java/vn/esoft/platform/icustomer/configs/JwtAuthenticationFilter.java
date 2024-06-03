package vn.esoft.platform.icustomer.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import vn.esoft.platform.icustomer.services.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
        JwtService jwtService,
        UserDetailsService userDetailsService,
        HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }



    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);
            final Map<String, List<String>> scope = jwtService.extractScope(jwt);
            if (!checkResourcePermission(request, scope)) {
                throw new AccessDeniedException("Not permission to access the resource");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

    private boolean checkResourcePermission(HttpServletRequest request, Map<String, List<String>> scope) {

        boolean rs = false;
        List<String> permissions = scope.get("permissions");
        List<String> resourceName = scope.get("nameResources");
        List<String> urlResources = scope.get("urlResources");
        String orgURI = request.getRequestURI();
        String orgFullPath = request.getRequestURL().toString();
        String orgMethod = request.getMethod();
        if (!permissions.contains(orgMethod)) return false;
        if(!checkURI(orgURI, resourceName)) return false;
        return rs;
    }
    private boolean checkURL(String orgFullPath, List<String> urlResources) {
        boolean rs = false;
        for (String urlResource : urlResources) {
            if(orgFullPath.contains(urlResource)) {
                rs = true;
                break;
            }
        }
        return rs;
    }

    private boolean checkURI(String oriURI, List<String> nameResources) {
        boolean rs = false;
        for (String urlResource : nameResources) {
            if(oriURI.contains(urlResource)) {
                rs = true;
                break;
            }
        }
        return rs;
    }
}
