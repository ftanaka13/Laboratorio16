package br.com.faculdadeimpacta.laboratorio16.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato
import br.com.faculdadeimpacta.laboratorio16.data.repositories.ContatoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContatoViewModel(private val repository: ContatoRepository) : ViewModel() {

    private var _lista = MutableLiveData<List<Contato>>(listOf())
    val lista: LiveData<List<Contato>> = _lista

    private var _contato = MutableLiveData<Contato>()
    val contato: LiveData<Contato> = _contato

    fun inserir(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.inserir(contato)
        }
    }

    fun atualizar(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.atualizar(contato)
        }
    }

    fun deletar(contato: Contato) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletar(contato)
        }
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            _lista.postValue(repository.getLista())
        }
    }

    fun getPorId(idContato: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _contato.postValue(repository.getPorId(idContato))
        }
    }
}

class ContatoViewModelFactory(private val repository: ContatoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContatoViewModel::class.java)) {
            return ContatoViewModel(repository) as T
        }
        throw IllegalArgumentException()
    }
}