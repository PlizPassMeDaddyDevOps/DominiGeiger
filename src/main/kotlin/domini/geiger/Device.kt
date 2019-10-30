package domini.geiger

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Device (var lat: Long = 0, var lng: Long = 0, var sievert: Int = 0){

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    constructor(): this(0, 0, 0)

}