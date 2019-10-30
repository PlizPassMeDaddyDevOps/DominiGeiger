package domini.geiger

object DtoConverter {

    fun transform(device: Device) : DeviceDto {

        return DeviceDto(
                lat = device.lat,
                lng = device.lng,
                sievert = device.sievert,
                id = device.id.toString()
        )
    }

    fun transform(device: Iterable<Device>) : List<DeviceDto>{

        return device.map { transform(it) }
    }
}