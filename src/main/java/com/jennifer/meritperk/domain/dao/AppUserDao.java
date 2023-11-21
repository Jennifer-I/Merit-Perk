package com.jennifer.meritperk.domain.dao;

import com.jennifer.meritperk.domain.entities.AppUserEntity;
import com.jennifer.meritperk.domain.entities.enums.Role;

public interface AppUserDao extends CrudDao<AppUserEntity, Long>{
    AppUserEntity findAppUserEntityByEmail(String email);
    AppUserEntity findAppUserEntityByEmailAndRole(String email, Role role);
}
