package com.jennifer.meritperk.usecase.services;

import com.jennifer.meritperk.domain.entities.SchoolEntity;

import java.util.List;

public interface SchoolService {

    String saveSchool(List<SchoolEntity> schoolEntities);

    int getSchoolCount();
}
