@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenValidator jwtTokenValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the JWT token from the request headers
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            // Validate the JWT token
            boolean isValidToken = jwtTokenValidator.validateToken(token);

            if (isValidToken) {
                // Extract the customer ID from the token and set it in the request context
                String customerId = jwtTokenValidator.extractCustomerId(token);
                request.setAttribute("customerId", customerId);
                // Proceed with the request
                filterChain.doFilter(request, response);
            } else {
                // Return authentication failure response
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
            }
        } else {
            // Return authentication failure response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
        }
    }
}
