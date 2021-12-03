package com.olx.actuator;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;

public class CustomInfoContributor implements InfoContributor{

	@Override
	public void contribute(Builder builder) {

			builder.withDetail("Name", builder.getClass().getName());
		
	}

	
	
}
