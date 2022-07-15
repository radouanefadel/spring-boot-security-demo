package io.relfadel.springboot_security_demo.users.data.repositories;

import io.relfadel.springboot_security_demo.users.data.entities.User;
import io.relfadel.springboot_security_demo.common.Paths;
import io.relfadel.springboot_security_demo.utils.CSVReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {
	@Autowired
	private UserRepository repository;

	private Boolean  dataLoaded = false;

	@BeforeEach
	public void init() {
		if (!dataLoaded) {
			Map<Integer, List<String>> records = CSVReader.getRecords(Paths.DATA_USERS);
			records.forEach((key, data) -> {
				User user = new User();
				user.setFirstName(data.toArray()[0].toString());
				user.setLastName(data.toArray()[1].toString());
				user.setEmail(data.toArray()[2].toString());
				user.setPassword(
						new BCryptPasswordEncoder().encode(data.toArray()[3].toString())
				);
				user.setEnabled(true);
				user.setLocked(false);
				this.repository.save(user);
			});
			dataLoaded = true;
		}
	}


	@CsvSource({
			"smarrian0@twitpic.com,TRUE",
			"r.elfadel@mail.com,FALSE",
			"radouane.elfadel@example2.com,FALSE",
			"heblein3@mit.edu,TRUE",
			"cappleton7@vimeo.com,TRUE"
	})
	@DisplayName("Retrieving a user by email")
	@ParameterizedTest()
	void findByEmail(String email, Boolean expected) {
		Optional<User> user = this.repository.findByEmail(email);
		final boolean exists = user.isPresent();
		assertEquals(expected, exists);
	}
}