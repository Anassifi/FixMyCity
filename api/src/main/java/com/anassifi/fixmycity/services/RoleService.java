package com.anassifi.fixmycity.services;

import com.anassifi.fixmycity.repositories.RoleRepository;
import com.anassifi.fixmycity.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService implements Services<Role> {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role get(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Long id) {
        try {
            roleRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
