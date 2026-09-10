package com.project.device_service;

import com.project.device_service.entity.Device;
import com.project.device_service.model.DeviceType;
import com.project.device_service.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DeviceServiceApplicationTests {

	public static final int NUMBER_OF_DEVICES = 10;
	public static final int USERS = 32;

	@Autowired
	private DeviceRepository deviceRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@Disabled
	void createDevices() {
		for (int i = 1; i <= NUMBER_OF_DEVICES; i++) {
			var device = Device.builder()
					.name("Device" + i)
					.type(DeviceType.values()[i % DeviceType.values().length])
					.location("Location" + ((i % 3) + 1))
					.userId((long) (i + USERS))
					.build();
			deviceRepository.save(device);
		}
		log.info("Device Repository has been populated");
	}


}
