package br.com.faculdadeimpacta.laboratorio16.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato
import br.com.faculdadeimpacta.laboratorio16.databinding.ContatoItemBinding

class ContatoAdapter(private val listaContato: List<Contato>, private val acao: (Int) -> Unit) :
    RecyclerView.Adapter<ContatoAdapter.ContatoVH>() {

    inner class ContatoVH(private val binding: ContatoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(contato: Contato) {
            binding.contato = contato
            binding.constraintLayout.setOnClickListener { acao(contato.idContato) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContatoItemBinding.inflate(inflater, parent, false)
        return ContatoVH(binding)
    }

    override fun onBindViewHolder(holder: ContatoVH, position: Int) {
        holder.onBind(listaContato[position])
    }

    override fun getItemCount(): Int = listaContato.size
}