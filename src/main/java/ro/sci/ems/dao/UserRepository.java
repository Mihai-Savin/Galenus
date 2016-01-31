package ro.sci.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sci.ems.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
