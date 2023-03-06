package br.senai.sp.jandira.lazycolumnroom.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.senai.sp.jandira.lazycolumnroom.model.Product

// classe que herda outra classe
@Database(entities = [Product::class], version = 1)
abstract class ProductDb: RoomDatabase() {

    // 3
    // função que retorna a interface do banco de dados
    abstract fun productDao(): ProductDao

    companion object {
        // variável que guarda a instância do banco de dados
        private lateinit var instance: ProductDb

        fun getDatabase(context: Context): ProductDb {
            // verifica se a instância já foi criada
            if(!::instance.isInitialized) {
                // a instância precisa do contexto, classe e nome do banco de dados
                instance = Room
                    .databaseBuilder(
                        context,
                        ProductDb::class.java,
                        "db_product"
                    )
                    .allowMainThreadQueries()
                    // se algo do banco de dados mudar, os dados atuais serão destruídos (NÃO USAR EM UMA APLICAÇÃO REAL)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}

// 1