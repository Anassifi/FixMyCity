package com.anassifi.fixmycity.repositories;

import com.anassifi.fixmycity.models.Administration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrationRepository extends JpaRepository<Administration, Long> {

}
