package logger

import log.builder.logger

fun infoLog(msg: String, relation: Map<String, String> = mapOf()) {

    val relationMessage = relation.map { "${ it.key } -> ${ it.value }" }.joinToString(separator = "\n")

    logger.info {
        """
            $msg
            $relationMessage
        """.replace(" ", "")
    }
}
