package io.relfadel.springboot_security_demo.users.data.entities;

import io.relfadel.springboot_security_demo.users.enums.RoleNames;
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
@Table(name = "security_roles")
public class Role {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "uuid", columnDefinition = "char(36)")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID uuid;

	@Enumerated(EnumType.STRING)
	private RoleNames name;

	@ManyToMany
	@JoinTable(
			name = "security_user_roles",
			joinColumns = @JoinColumn(name = "role_uuid"),
			inverseJoinColumns = @JoinColumn(name = "user_uuid")
	)
	private List<User> users;
}
