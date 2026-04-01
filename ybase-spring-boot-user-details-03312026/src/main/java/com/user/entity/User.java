package com.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
    
}