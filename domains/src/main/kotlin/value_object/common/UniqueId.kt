package value_object.common

import java.util.*

data class UniqueId (val value: String = UUID.randomUUID().toString())
