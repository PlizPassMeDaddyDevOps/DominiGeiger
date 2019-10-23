package domini.geiger

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/devices")
class DeviceController(val deviceRepository: DeviceRepository) {

    @GetMapping("/devices")
    fun all(): MutableIterable<Device> = this.deviceRepository.findAll()

    @PostMapping("/devices")
    fun create(@RequestBody dto: DeviceDto){
        val entity = Device(dto.lat!!, dto.lng!!, dto.sievert!!)
        deviceRepository.save(entity)
    }
}