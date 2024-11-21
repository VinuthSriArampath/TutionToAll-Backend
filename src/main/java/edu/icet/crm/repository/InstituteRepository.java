package edu.icet.crm.repository;

import edu.icet.crm.entity.InstituteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstituteRepository extends CrudRepository<InstituteEntity,String> {
    InstituteEntity findByid(String id);
    List<InstituteEntity> findAll();
}
