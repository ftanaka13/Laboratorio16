package br.com.faculdadeimpacta.laboratorio16.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contato(
    @PrimaryKey(autoGenerate = true) val idContato: Int = 0,
    var nome: String,
    var telefone: String,
    var idade: Int
)
