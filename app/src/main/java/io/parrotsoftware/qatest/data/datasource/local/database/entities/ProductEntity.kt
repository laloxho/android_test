package io.parrotsoftware.qatest.data.datasource.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.parrotsoftware.qatest.domain.models.Category

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val id: String,
    @ColumnInfo(name = "product_name")
    val name: String,
    val description: String,
    val image: String,
    val price: Float,
    val isAvailable: Boolean,
    @Embedded val category: Category
)
