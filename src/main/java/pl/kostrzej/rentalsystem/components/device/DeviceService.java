package pl.kostrzej.rentalsystem.components.device;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class DeviceService {

    private DeviceRepository deviceRepository;


    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    public void deleteById(long id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public boolean existsById(long id) {
        return deviceRepository.existsById(id);
    }

    public Device findById(long id) {
        return deviceRepository.findById(id);
    }
}
