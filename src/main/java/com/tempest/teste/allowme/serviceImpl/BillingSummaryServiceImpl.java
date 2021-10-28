package com.tempest.teste.allowme.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tempest.teste.allowme.entity.BillingSummary;
import com.tempest.teste.allowme.repository.BillingSummaryRepository;
import com.tempest.teste.allowme.service.BillingSummaryService;

@Service
public class BillingSummaryServiceImpl implements BillingSummaryService {

	BillingSummaryRepository billingSummaryRepository;
	
	@Autowired
	public BillingSummaryServiceImpl(BillingSummaryRepository billingSummaryRepository) {
		this.billingSummaryRepository = billingSummaryRepository;
	}
	
	public void persitBillingSummary(BillingSummary billingSummary) {
		billingSummaryRepository.save(billingSummary);
	}

}
