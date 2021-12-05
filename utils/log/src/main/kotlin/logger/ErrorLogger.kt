package logger

import builder.logger

fun errorLog(e: Throwable, msg: String, relation: Map<String, String> = mapOf()) {

    val relationMessage = relation.map { "${ it.key } -> ${ it.value }" }.joinToString(separator = "\n")

    logger.error(e) {
        """
            $msg
            $relationMessage
        """.replace(" ", "")
    }
}
