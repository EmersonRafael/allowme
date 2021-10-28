package com.tempest.teste.allowme.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tempest.teste.allowme.entity.Services;
import com.tempest.teste.allowme.repository.ServicesServiceRepository;
import com.tempest.teste.allowme.service.ServicesService;

@Service
public class ServicesServiceImpl implements ServicesService{

	private List<Services> listaSevices = new CopyOnWriteArrayList<Services>();
	ServicesServiceRepository servicesServiceRepository;
	
	public ServicesServiceImpl(ServicesServiceRepository servicesServiceRepository) {
		this.servicesServiceRepository = servicesServiceRepository;
	}
	
	public Services getService(String nome) {
		
		Optional<Services> apt = this.listaSevices.stream().filter(sev -> sev.getName().equals(nome)).findFirst();
		if(apt.isPresent())
				return apt.get();
		
		return null;
	}
	
	@PostConstruct
	@Scheduled(fixedDelayString = "6000000")
	public void refresh() {
		List<Services> listaSevices = servicesServiceRepository.findAll();
		this.listaSevices.clear();
		this.listaSevices.addAll(listaSevices);
	}
	
}
