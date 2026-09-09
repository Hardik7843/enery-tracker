package com.project.device_service.service;

import com.project.device_service.dto.DeviceDto;
import com.project.device_service.entity.Device;
import com.project.device_service.exception.ResourceNotFound;
import com.project.device_service.repository.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DeviceService {
    private DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    private DeviceDto mapToDto(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setName(device.getName());
        dto.setType(device.getType());
        dto.setLocation(device.getLocation());
        dto.setUserId(device.getUserId());
        return dto;
    }
    public DeviceDto getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Device not found with id " + id));
        return mapToDto(device);
    }

    public DeviceDto createDevice(DeviceDto input) {
        log.info("input={}", input);
        Device device = new Device();
        device.setName(input.getName());
        device.setType(input.getType());
        device.setLocation(input.getLocation());
        device.setUserId(input.getUserId());

        final Device savedDevice = deviceRepository.save(device);
        return mapToDto(savedDevice);
    }

    public DeviceDto updateDevice(Long id, DeviceDto input) {
        Device existing = deviceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Device not found with id " + id));

        existing.setName(input.getName());
        existing.setType(input.getType());
        existing.setLocation(input.getLocation());
        existing.setUserId(input.getUserId());

        final Device updatedDevice = deviceRepository.save(existing);
        return mapToDto(updatedDevice);
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new ResourceNotFound("Device not found with id " + id);
        }
        deviceRepository.deleteById(id);
    }

//    public List<DeviceDto> getAllDevicesByUserId(Long userId) {
//        List<Device> devices = deviceRepository.findAllByUserId(userId);
//        return devices.stream()
//                .map(this::mapToDto)
//                .toList();
//    }
}
