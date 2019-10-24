package domini.geiger

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Device (val lat: Long = 0, val lng: Long = 0, val sievert: Long = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var deviceId: Long = 0

    constructor(): this(0, 0, 0)

}