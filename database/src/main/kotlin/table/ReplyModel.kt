package table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object ReplyModel : Table("replies"){
    val id = varchar("id", 36)
    val commentId = varchar("comment_id", 36)
    val body = text("body")
    val createdAt = datetime("created_at")
    val modifiedAt = datetime("modified_at")

    override val primaryKey = PrimaryKey(id)
}
