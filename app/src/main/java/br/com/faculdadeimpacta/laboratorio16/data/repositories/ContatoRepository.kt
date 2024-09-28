package br.com.faculdadeimpacta.laboratorio16.data.repositories

import br.com.faculdadeimpacta.laboratorio16.data.datasources.room.daos.ContatoDao
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato

class ContatoRepository(private val dao: ContatoDao) {

    suspend fun inserir(contato: Contato) {
        dao.inserir(contato)
    }

    suspend fun atualizar(contato: Contato) {
        dao.atualizar(contato)
    }

    suspend fun deletar(contato: Contato) {
        dao.deletar(contato)
    }

    suspend fun getLista(): List<Contato> = dao.getLista()

    suspend fun getPorId(idContato: Int): Contato = dao.getPorId(idContato)

}