package br.com.faculdadeimpacta.laboratorio16.data.datasources.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.faculdadeimpacta.laboratorio16.data.datasources.room.daos.ContatoDao
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato
import kotlin.concurrent.Volatile

@Database(entities = [Contato::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getContatoDao(): ContatoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "appDatabase"
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}