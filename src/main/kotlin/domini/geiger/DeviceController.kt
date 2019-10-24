package domini.geiger

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/devices")
class DeviceController(val deviceRepository: DeviceRepository) {

    /*REST Endepunkt for ny geigerteller. Svare på POST til /devices og returnere et objekt med en unik identifikator "deviceId"
    REST Endepunkt for ny måling av stråling og lokasjon (lat, lng, sievert). POST /devices/{deviceId}/measurements
    REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements
    REST Endepunkt for å liste alle målere. GET til /devices
    */
    @GetMapping("/all")
    fun allDevice(): MutableIterable<Device> = this.deviceRepository.findAll()

    @PostMapping("/{deviceId}")
    fun create(@RequestBody dto: DeviceDto) {
        val entity = Device(dto.lat!!, dto.lng!!, dto.sievert!!)
        deviceRepository.save(entity)
        val response = DtoConverter.transform(entity)
        println("just saved device nr: " + response.deviceId + "with latitude: " + response.lat.toString() + " and longditude: "
        + response.lng.toString() + "showing sievert :" + response.sievert.toString())
    }

   // @GetMapping("/devices/{deviceId}")

}