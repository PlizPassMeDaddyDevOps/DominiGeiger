package domini.geiger

class DeviceDto(
        val lat: Long? = 0,
        val lng: Long? = 0,
        val sievert: Long? =0,
        var deviceId: String? = null
)