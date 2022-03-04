package db

import db.seeding.ArticleModelSeeder
import db.seeding.CommentModelSeeder
import db.seeding.ReplyModelSeeder
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import javax.sql.DataSource

fun DataSource.migrate() {
    val flyway = Flyway.configure()
        .dataSource(this)
        .load()

    flyway.info()
    flyway.migrate()
}


val seeding = listOf(ArticleModelSeeder, CommentModelSeeder, ReplyModelSeeder)

fun Database.seeding() = seeding.forEach { it.seeding(database = this) }
