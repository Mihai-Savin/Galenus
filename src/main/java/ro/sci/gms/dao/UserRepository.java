package ro.sci.gms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.sci.gms.domain.User;

public interface UserRepository extends JpaRepository<User, String>{

}
