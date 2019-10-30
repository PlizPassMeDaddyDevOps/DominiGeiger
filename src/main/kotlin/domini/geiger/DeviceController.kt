package domini.geiger

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.net.URI

@RestController
@RequestMapping("/api/devices")
class DeviceController(val deviceRepository: DeviceRepository) {

    /*
    REST Endepunkt for ny måling av stråling og lokasjon (lat, lng, sievert). POST /devices/{deviceId}/measurements
    REST Endepunkt for å hente en eller flere målinger for en device. GET til /devices/{id}/measurements

    */
    @GetMapping
    fun allDevice(): MutableIterable<Device> = this.deviceRepository.findAll()

    @PostMapping
    fun createDevice(
            @RequestBody dto: DeviceDto
    ) : ResponseEntity<Void> {

        if (dto.id != null) {
            return ResponseEntity.status(400).build()
        }
        val entity = Device(dto.lat!!, dto.lng!!, dto.sievert!!)
        deviceRepository.save(entity)

        return ResponseEntity.created(URI.create("/devices/" + entity.id)).build()
    }

    @GetMapping(path = ["/{id}"])
    fun getDeviceById(
            @PathVariable("id")
            pathId: String
    ): ResponseEntity<DeviceDto> {

        val id: Long
        try {
            id =pathId.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        val device = deviceRepository.findById(id).orElse(null)
                ?: return ResponseEntity.status(404).build()

        return ResponseEntity.status(200).body(DtoConverter.transform(device))

    }

    @PutMapping(path = ["/{id}"])
    fun updateById(
            @PathVariable("id")
            pathId: String,
            dto: DeviceDto
    ): ResponseEntity<Void> {
        val id: Long
        try {
            id = pathId.toLong()
        }catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if(dto.id == null){
            return ResponseEntity.status(400).build()
        }

        if(dto.id != pathId){
            return ResponseEntity.status(409).build()
        }

        val device = deviceRepository.findById(id).orElse(null)
                ?: return ResponseEntity.status(404).build()

        device.lat = dto.lat!!
        device.lng = dto.lng!!
        device.sievert  = dto.sievert!!

        deviceRepository.save(device)

        return ResponseEntity.status(204).build()
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
            @PathVariable("id")
            pathId: String
    ): ResponseEntity<Void> {

        val id: Long
        try {
            id = pathId.toLong()
        } catch (e: Exception) {
            return ResponseEntity.status(400).build()
        }

        if (!deviceRepository.existsById(id)) {
            return ResponseEntity.status(404).build()
        }

        deviceRepository.deleteById(id)

        return return ResponseEntity.status(204).build()
    }

}