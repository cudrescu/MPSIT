package acs.ssa.mpsit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import acs.ssa.mpsit.model.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer>, JpaSpecificationExecutor<NotificationEntity> {

}
