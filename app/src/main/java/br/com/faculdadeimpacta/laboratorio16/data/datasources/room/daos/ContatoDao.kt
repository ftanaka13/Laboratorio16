package br.com.faculdadeimpacta.laboratorio16.data.datasources.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato

@Dao
interface ContatoDao {

    @Insert
    suspend fun inserir(contato: Contato)

    @Update
    suspend fun atualizar(contato: Contato)

    @Delete
    suspend fun deletar(contato: Contato)

    @Query("SELECT * FROM Contato")
    suspend fun getLista(): List<Contato>

    @Query("SELECT * FROM Contato WHERE idContato = :idContato")
    suspend fun getPorId(idContato: Int): Contato

}