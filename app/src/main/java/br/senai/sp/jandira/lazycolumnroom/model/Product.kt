package br.senai.sp.jandira.lazycolumnroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// classe para armazenar dados
@Entity(tableName = "tbl_product")
data class Product(
    // define a variável como uma chave primária gerada automaticamente
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var productName: String = "Product Name",
    @ColumnInfo(name = "description") var productDescription: String = "Product Description",
    @ColumnInfo(name = "price") var productPrice: Double = 0.0
)
