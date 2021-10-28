package com.tempest.teste.allowme.repository;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tempest.teste.allowme.entity.ServiceRequests;

@Repository
public interface ServiceRequestsRepository extends JpaRepository<ServiceRequests, Long>{

	@Modifying
	@Transactional
	@Query("FROM ServiceRequests sR WHERE sR.created BETWEEN :start AND :end")
	List<ServiceRequests> getForDate(LocalDateTime start, LocalDateTime end);
	
}
