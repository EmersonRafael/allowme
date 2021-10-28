package com.tempest.teste.allowme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempest.teste.allowme.entity.Services;

@Repository
public interface ServicesServiceRepository extends JpaRepository<Services, Long>{

}
