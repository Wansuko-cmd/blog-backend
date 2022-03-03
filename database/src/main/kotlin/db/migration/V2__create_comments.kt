package db.migration

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import table.CommentModel

@Suppress("ClassName", "unused")
class V2__create_comments : BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction { SchemaUtils.create(CommentModel) }
    }
}
