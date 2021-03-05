package org.neethaudupi.hrms.repository;

import org.neethaudupi.hrms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsername(String username);
}
