package com.olx.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import com.olx.repository.AdvertiseRepo;

@Component
public class AdvertiseStatsActuator implements InfoContributor {

	@Autowired
	AdvertiseRepo advertiseRepo;
	
	@Override
	public void contribute(Builder builder) {
		builder.withDetail("Total Entities",advertiseRepo.count());
		
	}

	
}
