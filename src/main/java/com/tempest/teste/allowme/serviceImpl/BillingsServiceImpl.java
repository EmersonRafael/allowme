package com.tempest.teste.allowme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.teste.allowme.entity.Billings;
import com.tempest.teste.allowme.repository.BillingsRepository;
import com.tempest.teste.allowme.service.BillingsService;

@Service
public class BillingsServiceImpl implements BillingsService {

	BillingsRepository billingsRepository;
	
	@Autowired
	public  BillingsServiceImpl(BillingsRepository billingsRepository) {
		this.billingsRepository = billingsRepository;
	}
	
	public void persitBilling(Billings billings) {
		billingsRepository.save(billings);
	}

}
