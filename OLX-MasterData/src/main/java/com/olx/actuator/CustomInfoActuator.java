package com.olx.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.olx.repository.MasterDataRepo;

@Component
public class CustomInfoActuator implements InfoContributor {

	@Autowired
	MasterDataRepo masterRepo;
	
	@Override
	public void contribute(Builder builder) {
		
		builder
		.withDetail("Total Entities",masterRepo.count());
		
	}

	
}
