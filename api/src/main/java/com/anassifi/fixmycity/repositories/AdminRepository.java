package com.anassifi.fixmycity.repositories;

import com.anassifi.fixmycity.models.Admin;
import com.anassifi.fixmycity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<User> findByLogin(String login);

}
