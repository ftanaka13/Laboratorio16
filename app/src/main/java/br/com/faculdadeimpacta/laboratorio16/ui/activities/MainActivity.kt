package br.com.faculdadeimpacta.laboratorio16.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.faculdadeimpacta.laboratorio16.R
import br.com.faculdadeimpacta.laboratorio16.data.datasources.room.daos.ContatoDao
import br.com.faculdadeimpacta.laboratorio16.data.datasources.room.database.AppDatabase
import br.com.faculdadeimpacta.laboratorio16.data.repositories.ContatoRepository

class MainActivity : AppCompatActivity() {

    private val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstance(this)
    }
    private val contatoDao: ContatoDao by lazy { appDatabase.getContatoDao() }
    val contatoRepository: ContatoRepository by lazy { ContatoRepository(contatoDao) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}