package com.miras.studyflow.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.miras.studyflow.models.Task


class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {


    companion object {

        // Название файла базы данных.
        private const val DATABASE_NAME =
            "studyflow.db"

        private const val DATABASE_VERSION =
            1


        // Таблица заданий.
        const val TABLE_TASKS =
            "tasks"


        const val COLUMN_ID =
            "id"

        const val COLUMN_TITLE =
            "title"

        const val COLUMN_DESCRIPTION =
            "description"

        const val COLUMN_CATEGORY =
            "category"

        const val COLUMN_PRIORITY =
            "priority"

        const val COLUMN_DEADLINE =
            "deadline"

        const val COLUMN_COMPLETED =
            "completed"
    }


    override fun onCreate(db: SQLiteDatabase) {

        val createTableQuery = """

            CREATE TABLE $TABLE_TASKS (

                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,

                $COLUMN_TITLE TEXT NOT NULL,

                $COLUMN_DESCRIPTION TEXT,

                $COLUMN_CATEGORY TEXT,

                $COLUMN_PRIORITY TEXT,

                $COLUMN_DEADLINE TEXT,

                $COLUMN_COMPLETED INTEGER DEFAULT 0
            )

        """.trimIndent()


        db.execSQL(
            createTableQuery
        )
    }


    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {

        db.execSQL(
            "DROP TABLE IF EXISTS $TABLE_TASKS"
        )

        onCreate(db)
    }


    // ------------------------------------------------
    // ADD TASK
    // ------------------------------------------------

    fun addTask(task: Task): Long {

        val database =
            writableDatabase


        val values =
            ContentValues()


        values.put(
            COLUMN_TITLE,
            task.title
        )


        values.put(
            COLUMN_DESCRIPTION,
            task.description
        )


        values.put(
            COLUMN_CATEGORY,
            task.category
        )


        values.put(
            COLUMN_PRIORITY,
            task.priority
        )


        values.put(
            COLUMN_DEADLINE,
            task.deadline
        )


        values.put(
            COLUMN_COMPLETED,
            if (task.completed) 1 else 0
        )


        return database.insert(
            TABLE_TASKS,
            null,
            values
        )
    }


    // ------------------------------------------------
    // GET ALL TASKS
    // ------------------------------------------------

    fun getAllTasks(): MutableList<Task> {

        val taskList =
            mutableListOf<Task>()


        val database =
            readableDatabase


        val cursor =
            database.rawQuery(
                """
                SELECT *
                FROM $TABLE_TASKS
                ORDER BY $COLUMN_ID DESC
                """.trimIndent(),
                null
            )


        if (cursor.moveToFirst()) {

            do {

                val task =
                    Task(

                        id =
                            cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_ID
                                )
                            ),

                        title =
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_TITLE
                                )
                            ),

                        description =
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_DESCRIPTION
                                )
                            ) ?: "",

                        category =
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_CATEGORY
                                )
                            ) ?: "",

                        priority =
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_PRIORITY
                                )
                            ) ?: "MEDIUM",

                        deadline =
                            cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_DEADLINE
                                )
                            ) ?: "",

                        completed =
                            cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                    COLUMN_COMPLETED
                                )
                            ) == 1
                    )


                taskList.add(
                    task
                )

            }
            while (
                cursor.moveToNext()
            )
        }


        cursor.close()

        return taskList
    }


    // ------------------------------------------------
    // UPDATE TASK
    // ------------------------------------------------

    fun updateTask(task: Task): Int {

        val database =
            writableDatabase


        val values =
            ContentValues()


        values.put(
            COLUMN_TITLE,
            task.title
        )


        values.put(
            COLUMN_DESCRIPTION,
            task.description
        )


        values.put(
            COLUMN_CATEGORY,
            task.category
        )


        values.put(
            COLUMN_PRIORITY,
            task.priority
        )


        values.put(
            COLUMN_DEADLINE,
            task.deadline
        )


        values.put(
            COLUMN_COMPLETED,
            if (task.completed) 1 else 0
        )


        return database.update(

            TABLE_TASKS,

            values,

            "$COLUMN_ID = ?",

            arrayOf(
                task.id.toString()
            )
        )
    }


    // ------------------------------------------------
    // DELETE
    // ------------------------------------------------

    fun deleteTask(id: Int): Int {

        val database =
            writableDatabase


        return database.delete(

            TABLE_TASKS,

            "$COLUMN_ID = ?",

            arrayOf(
                id.toString()
            )
        )
    }


    // ------------------------------------------------
    // TASK COUNT
    // ------------------------------------------------

    fun getTotalTaskCount(): Int {

        val database =
            readableDatabase


        val cursor =
            database.rawQuery(

                """
                SELECT COUNT(*)
                FROM $TABLE_TASKS
                """.trimIndent(),

                null
            )


        var result = 0


        if (
            cursor.moveToFirst()
        ) {

            result =
                cursor.getInt(0)
        }


        cursor.close()

        return result
    }


    // ------------------------------------------------
    // COMPLETED COUNT
    // ------------------------------------------------

    fun getCompletedTaskCount(): Int {

        val database =
            readableDatabase


        val cursor =
            database.rawQuery(

                """
                SELECT COUNT(*)
                FROM $TABLE_TASKS
                WHERE $COLUMN_COMPLETED = 1
                """.trimIndent(),

                null
            )


        var result = 0


        if (
            cursor.moveToFirst()
        ) {

            result =
                cursor.getInt(0)
        }


        cursor.close()

        return result
    }
}