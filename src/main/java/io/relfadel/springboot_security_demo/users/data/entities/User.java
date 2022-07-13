package io.relfadel.springboot_security_demo.users.data.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "security_users")
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuid", columnDefinition = "char(36)")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID uuid;

	@Column(name = "first_name", length = 31, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 31, nullable = false)
	private String lastName;

	@Column(length = 127, nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(nullable = false)
	private Boolean locked;
}