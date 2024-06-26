package com.anassifi.fixmycity.Seeders;

import com.anassifi.fixmycity.models.Administration;
import com.anassifi.fixmycity.models.Specialisation;
import com.anassifi.fixmycity.services.AdministrationService;
import com.anassifi.fixmycity.services.RoleService;
import com.anassifi.fixmycity.services.SpecialisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class AdministrationSeeder {

    @Autowired
    private final AdministrationService administrationService;
    @Autowired
    private final SpecialisationService specialisationService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleService roleService;

    public void Seed(int count) {
        for (int i = 1; i <= count; i++) {
            Administration administration = new Administration();
            administration.setRole(roleService.getByName("ROLE_ADMINISTRATION"));
            administration.setLogin("administration_" + i);
            administration.setPassword(passwordEncoder.encode("pass1234_" + i));
            administration.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            administration.setEmail("administration_" + i + "@email.com");
            administration.setFirstName(null);
            administration.setIsActive(true);
            administration.setLastName(null);
            administration.setLat("34.032109");
            administration.setLan("-6.806705");
            ArrayList<String> phones = new ArrayList<String>();
            phones.add("+212061234560" + i);
            phones.add("+212061234561" + i);
            phones.add("+212061234563" + i);
            administration.setPhones(phones);
            List<Specialisation> specialisations = specialisationService.getAll();
            int rand = new Random().nextInt(specialisations.size());
            administration.setSpecialisation(specialisationService.getAll().get(rand));
            administrationService.addOrUpdate(administration);
        }
    }
}
