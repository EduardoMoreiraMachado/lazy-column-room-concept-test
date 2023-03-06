package br.senai.sp.jandira.lazycolumnroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.senai.sp.jandira.lazycolumnroom.model.Product

// interface do Room
@Dao
interface ProductDao {

    // retorna o ID do registro criado
    @Insert
    fun save(product: Product): Long

    // retorna a quantidade de registros que foram deletados (opcional)
    @Delete
    fun delete(product: Product): Int

    // retorna uma lista de registros
    @Query("select * from tbl_product order by name asc")
    fun getAll(): List<Product>

}

// 2