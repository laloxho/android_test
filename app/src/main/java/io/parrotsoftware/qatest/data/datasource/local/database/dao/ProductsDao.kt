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

    @Query("SELECT * FROM products")
    suspend fun getProducts(): List<ProductEntity>

    @Query("DELETE FROM products")
    suspend fun deleteAll()

    @Query("SELECT * FROM products WHERE product_id=:id")
    suspend fun getProductById(id: String): ProductEntity

    @Query("UPDATE products SET isAvailable=:status WHERE product_id=:id")
    suspend fun updateProduct(status: Int, id: String)
}
