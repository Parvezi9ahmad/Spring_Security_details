package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import in.ashokit.entity.Users;

public interface UserRepo extends JpaRepository<Users,Integer> {

	Users findByUsername(String username);
}
