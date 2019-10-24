package domini.geiger

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DbSeeder(val deviceRepository: DeviceRepository) : CommandLineRunner {
    override fun run(vararg p0: String?) {
        this.deviceRepository.deleteAll()

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
    }

}