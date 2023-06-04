package io.parrotsoftware.qatest.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.parrotsoftware.qatest.data.datasource.local.database.dao.ProductsDao
import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productsDao(): ProductsDao

    companion object {
        fun build(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "qa_test_database.db")
                .build()
    }
}
