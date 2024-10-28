package edu.icet.crm.repository;

import edu.icet.crm.entity.InstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface InstituteRepository extends JpaRepository<InstituteEntity,String> {
}
