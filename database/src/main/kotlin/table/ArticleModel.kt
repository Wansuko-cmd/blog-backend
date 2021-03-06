package table

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object ArticleModel : Table("articles") {
    val id = varchar("id", 36)
    val thumbnailPath = text("thumbnail_path").nullable()
    val title = text("title")
    val body = text("body")
    val goodCount = integer("good_count")
    val createdAt = datetime("created_at")
    val modifiedAt = datetime("modified_at")

    override val primaryKey = PrimaryKey(id)
}
