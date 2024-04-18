package com.anassifi.fixmycity.repositories;

import com.anassifi.fixmycity.models.Specialisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialisationRepository extends JpaRepository<Specialisation, Long> {
}
