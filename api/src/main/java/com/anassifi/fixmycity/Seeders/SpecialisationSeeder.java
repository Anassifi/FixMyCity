package com.anassifi.fixmycity.Seeders;

import com.anassifi.fixmycity.models.Specialisation;
import com.anassifi.fixmycity.services.ProblemService;
import com.anassifi.fixmycity.services.SpecialisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class SpecialisationSeeder {

    @Autowired
    private SpecialisationService specialisationService;
    @Autowired
    private ProblemService problemService;

    public void Seed(int count) {
        for (int i = 1; i <= count; i++) {
            Specialisation specialisation = new Specialisation();
            specialisation.setName("specialisation_" + i);
            specialisation.setIcon(
                    "https://store-images.s-microsoft.com/image/apps.14591.9a19b1cb-7ffc-4b32-bf97-25ef75ec69a3.753de052-0f07-4e5e-a20d-e4d5688136af.e2247862-7caa-44e8-8915-ca934ab9b663.png");
            specialisation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            specialisationService.addOrUpdate(specialisation);
        }
    }
}
