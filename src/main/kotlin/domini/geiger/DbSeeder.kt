package domini.geiger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class DbSeeder(
        @Autowired val deviceRepository: DeviceRepository
)  {

    @PostConstruct
     fun initialize() {
        deviceRepository.run {
            deleteAll()
            save(Device(1, 1, 1))
            save(Device(2, 2, 2))
            save(Device(3, 3, 3))
            save(Device(4, 4, 4))
            save(Device(5, 5, 5))
            save(Device(6, 6, 6))
        }
/*
        val device1 = Device(1, 1, 1)
        val device2 = Device(2, 2, 2)
        val device3 = Device(3, 3, 3)
        val device4 = Device(4, 4, 4)

        val devices = mutableListOf<Device>()
        devices.add(device1)
        devices.add(device2)
        devices.add(device3)
        devices.add(device4)

        this.deviceRepository.saveAll(devices)
        println("----shits good-----")
   */ }

}