package com.tempest.teste.allowme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tempest.teste.allowme.entity.Billings;

@Repository
public interface BillingsRepository extends JpaRepository<Billings, Long> {

}
