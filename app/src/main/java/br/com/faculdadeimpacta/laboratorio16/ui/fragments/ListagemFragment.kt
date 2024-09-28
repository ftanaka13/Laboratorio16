package br.com.faculdadeimpacta.laboratorio16.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.faculdadeimpacta.laboratorio16.R
import br.com.faculdadeimpacta.laboratorio16.databinding.FragmentListagemBinding
import br.com.faculdadeimpacta.laboratorio16.ui.activities.MainActivity
import br.com.faculdadeimpacta.laboratorio16.ui.adapters.ContatoAdapter
import br.com.faculdadeimpacta.laboratorio16.ui.viewmodels.ContatoViewModel
import br.com.faculdadeimpacta.laboratorio16.ui.viewmodels.ContatoViewModelFactory

class ListagemFragment : Fragment() {

    private var _binding: FragmentListagemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContatoViewModel by activityViewModels {
        ContatoViewModelFactory((activity as MainActivity).contatoRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListagemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.lista.observe(viewLifecycleOwner) { lista ->
            with(binding.recyclerView) {
                adapter = ContatoAdapter(lista) { id ->
                    findNavController().navigate(
                        ListagemFragmentDirections.actionListagemFragmentToDetalhesFragment(
                            id
                        )
                    )
                }
                layoutManager = LinearLayoutManager(activity)
            }
        }
        viewModel.getLista()

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(
                ListagemFragmentDirections.actionListagemFragmentToDetalhesFragment(
                    0
                )
            )
        }
    }
}