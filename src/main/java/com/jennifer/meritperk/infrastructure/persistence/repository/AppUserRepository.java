package com.jennifer.meritperk.infrastructure.persistence.repository;

import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUserEntity,Long> {

    AppUserEntity findAppUserEntityByEmail(String email);

    AppUserEntity findAppUserEntityByEmailAndRole(String email, Role role);

}
