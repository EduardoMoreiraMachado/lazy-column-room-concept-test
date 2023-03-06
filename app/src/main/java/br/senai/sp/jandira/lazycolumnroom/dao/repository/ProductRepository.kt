package br.senai.sp.jandira.lazycolumnroom.dao.repository

import android.content.Context
import br.senai.sp.jandira.lazycolumnroom.dao.ProductDb
import br.senai.sp.jandira.lazycolumnroom.model.Product

// controla tudo que for relacionado a acesso de dados
class ProductRepository(context: Context) {

    // para saber qual é o banco de dados, fazer a conexão e usar os métodos da interface através de uma instância
    private val db = ProductDb.getDatabase(context).productDao()

    // a função não pode estar dentro de um método estático
    fun getProductsList(): List<Product> {
        return db.getAll()
    }

    fun save(product: Product): Long {
        return db.save(product)
    }
}

// 4