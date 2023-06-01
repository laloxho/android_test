package io.parrotsoftware.qatest.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.parrotsoftware.qatest.data.datasource.local.database.entities.ProductEntity

@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("Select * From products")
    suspend fun getProducts(): List<ProductEntity>
}