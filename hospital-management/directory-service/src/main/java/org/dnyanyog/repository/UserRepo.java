package org.dnyanyog.repository;

import java.util.List;
import java.util.Optional;

import org.dnyanyog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<Users,Long>{
	//List <Users> findByUserName(String userName);
//	Users findByEMail(String email);
	Optional<Users>  findByUserName(String userName);
	Optional<Users> findByUserId(long userId);
	boolean existsByUserName(String userName);
	
}
