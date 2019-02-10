package com.shiren.sjwt.dao;

import com.shiren.sjwt.dao.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionDao extends CrudRepository<Permission, Long> {

}
