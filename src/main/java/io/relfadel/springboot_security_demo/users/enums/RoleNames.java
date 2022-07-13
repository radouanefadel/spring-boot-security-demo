package io.relfadel.springboot_security_demo.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
public enum RoleNames {
	ADMIN("ADMIN"),
	AUTHOR("AUTHOR"),
	USER("USER"),
	SUPER_ADMIN("SUPER_ADMIN"),
	;

	@Getter
	private final String name;

	public static Stream<RoleNames> stream() {
		return Stream.of(RoleNames.values());
	}
}
