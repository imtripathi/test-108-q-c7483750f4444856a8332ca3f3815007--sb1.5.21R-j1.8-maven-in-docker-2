package org.codejudge.sb.repository;

import java.util.List;

import org.codejudge.sb.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	@Query(value = "select * from user where username=?1", nativeQuery = true)
	List<User> getUser(String username);

}
