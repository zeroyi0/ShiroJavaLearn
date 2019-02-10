package com.shiren.sjwt.dao;

import com.shiren.sjwt.dao.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

}
