package com.bcp.services.cross.exchangerate.repository;

import com.bcp.services.cross.exchangerate.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

  public Optional<Rol> findByName(String name);

}
