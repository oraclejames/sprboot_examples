'' Layer 0 — ఏమి చేస్తాం? ఎందుకు?  ''
Spring Boot project create చేయడం — ఇది foundation. ఇది లేకుండా ఏమీ పని చేయదు. ఇక్కడ మన project structure, dependencies అన్నీ set అవుతాయి.
Step 1 — start.spring.io కి వెళ్ళండి
1
Project Type ఎంచుకోండి
Maven Project ఎంచుకోండి. Maven = dependencies automatically download చేసే tool
2
Language: Java | Version: 17+
Java 17 stable version. Spring Boot 3.x కి minimum Java 17 కావాలి
3
Group: com.example | Artifact: crudapp
Group = మీ company/org name. Artifact = project name. ఇవి package names అవుతాయి
Step 2 — Dependencies Add చేయండి (చాలా ముఖ్యం!)
Spring Web — REST APIs తయారు చేయడానికి. లేకుండా @RestController పని చేయదు!
Spring Data JPA — Database తో communicate చేయడానికి. లేకుండా Repository పని చేయదు!
PostgreSQL Driver — Supabase/PostgreSQL connect చేయడానికి. ఇది లేకుండా DB connection వస్తుంది!
Lombok — Boilerplate code తగ్గించడానికి. getters/setters auto generate అవుతాయి
Spring Security — Authentication/Authorization కోసం (later)
Step 3 — pom.xml ఇలా ఉంటుంది
<dependencies>
  <!-- REST API కి - తప్పనిసరి -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
<!-- JPA/Hibernate DB కి - తప్పనిసరి -->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
<!-- Supabase PostgreSQL Driver - తప్పనిసరి -->
  <dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
  </dependency>
<!-- Lombok - Optional కానీ useful -->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
</dependencies>
Step 4 — application.yml తయారు చేయండి
⚠ src/main/resources/application.yml file లో ఈ code రాయండి. .properties కాదు, .yml వాడండి — clean గా ఉంటుంది!
spring:
  datasource:
    url: jdbc:postgresql://db.fkxwqbfhxcawxuzhjcfy.supabase.co:5432/postgres
    username: postgres
    password: YOUR_SUPABASE_PASSWORD   # ← మీ password!
    driver-class-name: org.postgresql.Driver
jpa:
    hibernate:
      ddl-auto: update   # Tables auto create చేస్తుంది!
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
server:
  port: 8080
✓ ddl-auto: update = మీరు Entity class రాస్తే, Supabase లో table automatically తయారవుతుంది!




Layer 1 — Entity: ఏమిటి? ఎందుకు? ఎక్కడ?
ఏమిటి: Database table ని Java class గా represent చేస్తుంది
ఎందుకు: Spring JPA ఈ class చూసి database లో table create చేస్తుంది
ఎక్కడ: com.example.crudapp.entity package లో
ఎప్పుడు: Data model define చేయాల్సినప్పుడు — మొదటగా చేయాల్సింది ఇదే!
Step 1 — entity package create చేయండి
IntelliJ లో: src/main/java/com/example/crudapp/ మీద right click → New → Package → "entity" అని type చేయండి
Step 2 — User.java class రాయండి (అన్ని annotations అర్థం తో)
@Entity                    // "ఈ class = DB table" అని Spring కి చెప్తుంది
@Table(name = "users")    // Supabase లో table name "users" అవుతుంది
@Data                      // Lombok: getters + setters auto తయారవుతాయి
@NoArgsConstructor         // Empty constructor — JPA కి కావాలి!
@AllArgsConstructor        // All fields constructor
public class User {
@Id                        // Primary Key column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;           // Auto increment — 1,2,3... automatically
@Column(nullable = false)  // NOT NULL constraint DB లో
    private String name;
@Column(unique = true, nullable = false)
    private String email;      // Unique — same email రెండుసార్లు రాదు
private String password;
}
చాలా ముఖ్యమైన annotations — గుర్తు పెట్టుకోండి!
@Entity లేకుండా → Spring ఈ class ని table గా recognize చేయదు. Error వస్తుంది!

@Id లేకుండా → "No identifier specified" error వస్తుంది!

@NoArgsConstructor లేకుండా → JPA data load చేయలేదు. Error వస్తుంది!

@Data (Lombok) లేకుండా → getters/setters manually రాయాలి (100+ lines!)

From <https://claude.ai/chat/90acbac4-4907-40f2-9f31-9e95dcddc2c0> 



Layer 2 — Repository: ఏమిటి? ఎందుకు? ఎక్కడ?
ఏమిటి: Database తో directly మాట్లాడే layer. SQL queries ఇక్కడ జరుగుతాయి
ఎందుకు: save(), findAll(), findById(), delete() — ఇవన్నీ automatic గా వస్తాయి. SQL రాయాల్సిన అవసరం లేదు!
ఎక్కడ: com.example.crudapp.repository package లో
ఎప్పుడు: Entity తర్వాత వెంటనే చేయాలి
Step 1 — repository package + UserRepository.java
@Repository               // "ఇది Data layer" అని Spring కి చెప్తుంది
public interface UserRepository
    extends JpaRepository<User, Long> {
    // ↑ User = Entity type, Long = ID type
// ఇవి Spring automatic గా implement చేస్తుంది!
    // save(user) → INSERT INTO users...
    // findAll() → SELECT * FROM users
    // findById(1L) → SELECT * FROM users WHERE id=1
    // deleteById(1L) → DELETE FROM users WHERE id=1
// Custom queries — method name వల్ల SQL auto తయారవుతుంది!
    Optional<User> findByEmail(String email);
    // ↑ Spring దీన్ని: SELECT * FROM users WHERE email=? గా convert చేస్తుంది
List<User> findByNameContaining(String name);
    // ↑ Spring దీన్ని: SELECT * FROM users WHERE name LIKE '%name%' చేస్తుంది
}
JpaRepository Magic — ఉచితంగా వచ్చే methods!
JpaRepository extend చేస్తే ఈ methods automatically వస్తాయి:

save(entity) → Create లేదా Update
findAll() → అందరినీ తీసుకోవడం
findById(id) → ఒకరిని తీసుకోవడం
existsById(id) → ఉన్నారా లేదా check
deleteById(id) → తొలగించడం
count() → ఎంత మంది ఉన్నారు

SQL ఒక్క line కూడా రాయాల్సిన అవసరం లేదు!
Custom Query రాయాల్సి వస్తే
@Query("SELECT u FROM User u WHERE u.email = :email")
Optional<User> findUserByEmail(@Param("email") String email);
// JPQL వాడతాం — SQL కాదు. "User" అంటే table కాదు, Class name!
⚠ @Query లో "User" అంటే DB table name కాదు — Java Class name! "users" table కి "User" class వాడాలి.


Layer 3 — Service: ఏమిటి? ఎందుకు? ఎక్కడ?
ఏమిటి: Business Logic ఉండే layer. "User create చేయడానికి ముందు email already ఉందా check చేయి" — ఇలాంటి rules ఇక్కడ
ఎందుకు: Controller directly Repository call చేయకూడదు! Middle layer కావాలి — separation of concerns
ఎక్కడ: com.example.crudapp.service package లో
ఎప్పుడు: Repository తర్వాత, Controller ముందు చేయాలి
Step 1 — UserService.java రాయండి
@Service                   // Spring container లో Bean గా register అవుతుంది
@RequiredArgsConstructor   // Lombok: constructor injection automatic
public class UserService {
private final UserRepository userRepository;
    // ↑ final = Dependency Injection. Spring automatic గా inject చేస్తుంది
// CREATE — కొత్త user save చేయడం
    public User createUser(User user) {
        // Business Logic: email already ఉందా check
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }
        return userRepository.save(user);
    }
// READ — అందరు users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
// READ — ఒక user
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
// UPDATE — user update చేయడం
    public User updateUser(Long id, User updatedUser) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found!"));
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        return userRepository.save(existing);
    }
// DELETE — user తొలగించడం
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
Service Layer ఎందుకు అవసరం? — చాలా ముఖ్యం!
Controller లో directly DB call చేయడం తప్పు ఎందుకంటే:

1. Business rules change అయితే అన్ని controllers మార్చాలి
2. Testing చేయలేం properly
3. Code duplicate అవుతుంది

Service layer = ఒక business rule ఒకే చోట → clean, testable, maintainable!



Layer 4 — Controller: ఏమిటి? ఎందుకు? ఎక్కడ?
ఏమిటి: HTTP Requests (GET, POST, PUT, DELETE) receive చేసే layer. ఇది "front door" లాంటిది
ఎందుకు: Postman/Browser నుండి వచ్చిన requests ఇక్కడ handle అవుతాయి. Service ని call చేసి response పంపిస్తుంది
ఎక్కడ: com.example.crudapp.controller package లో
ఎప్పుడు: Service తర్వాత చేయాలి — last coding step!
Step 1 — UserController.java రాయండి (Full CRUD)
@RestController            // @Controller + @ResponseBody = JSON auto return
@RequestMapping("/api/users") // Base URL path
@RequiredArgsConstructor
public class UserController {
private final UserService service;
// POST /api/users — CREATE
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        // @RequestBody = Postman లో Body లో పంపిన JSON → User object
        User saved = service.createUser(user);
        return ResponseEntity.status(201).body(saved); // 201 Created
    }
// GET /api/users — READ ALL
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAllUsers()); // 200 OK
    }
// GET /api/users/1 — READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        // @PathVariable = URL లో {id} ని Java variable కి bind చేస్తుంది
        return service.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); // 404
    }
// PUT /api/users/1 — UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }
// DELETE /api/users/1 — DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
Postman లో Test చేయడం — URL మరియు Method తప్పు చేయకండి!
Create: POST → http://localhost:8080/api/users
Body → raw → JSON: {"name":"Ravi","email":"ravi@test.com","password":"123"}

Get All: GET → http://localhost:8080/api/users

Get One: GET → http://localhost:8080/api/users/1

Update: PUT → http://localhost:8080/api/users/1
Body → raw → JSON: {"name":"Ravi Kumar","email":"ravi@test.com"}

Delete: DELETE → http://localhost:8080/api/users/1


Layer 5 — Security Config + Final Checklist
Security లేకుండా run చేస్తే 401/403 errors వస్తాయి. Testing కి security disable చేయడం లేదా properly configure చేయడం నేర్చుకుందాం!
SecurityConfig.java — Testing కి (403 Fix!)
@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
            .csrf(csrf -> csrf.disable())   // Testing కి csrf off
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()   // అందరికీ access — testing!
            );
        return http.build();
    }
}
✓ ఇది చేస్తే 403 Forbidden పోతుంది! Production లో మాత్రం security add చేయాలి.
Final — అన్ని Files Check చేయండి
✓
application.yml లో Supabase URL, username, password సరిగ్గా ఉన్నాయా?
✓
User.java లో @Entity, @Id annotations ఉన్నాయా?
✓
UserRepository JpaRepository extend చేసిందా?
✓
UserService లో @Service annotation ఉందా?
✓
UserController లో @RestController ఉందా?
✓
SecurityConfig లో permitAll() ఉందా?
Run చేయండి — mvn spring-boot:run
Console లో ఇవి వస్తే success:

"Started CrudappApplication in X seconds"
"HHH000204: Processing PersistenceUnitInfo [name: default]"
"Hibernate: create table if not exists users..."

ఇవి వస్తే మీ Supabase లో users table automatically create అయినట్టే!








 
