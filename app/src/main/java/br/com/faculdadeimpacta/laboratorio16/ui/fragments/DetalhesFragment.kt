package br.com.faculdadeimpacta.laboratorio16.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.faculdadeimpacta.laboratorio16.R
import br.com.faculdadeimpacta.laboratorio16.data.models.Contato
import br.com.faculdadeimpacta.laboratorio16.databinding.FragmentDetalhesBinding
import br.com.faculdadeimpacta.laboratorio16.ui.activities.MainActivity
import br.com.faculdadeimpacta.laboratorio16.ui.viewmodels.ContatoViewModel
import br.com.faculdadeimpacta.laboratorio16.ui.viewmodels.ContatoViewModelFactory

class DetalhesFragment : Fragment() {

    private var _binding: FragmentDetalhesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContatoViewModel by activityViewModels {
        ContatoViewModelFactory((activity as MainActivity).contatoRepository)
    }
    private val args: DetalhesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetalhesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        var contato = Contato(0, "", "", 0)

        if (args.contatoId != 0) {
            viewModel.contato.observe(viewLifecycleOwner) { contatoBD ->
                contato = contatoBD
                binding.contato = contato
            }
            viewModel.getPorId(args.contatoId)
        }

        binding.buttonDeletar.visibility = if (args.contatoId == 0) View.INVISIBLE else View.VISIBLE
        binding.buttonDeletar.setOnClickListener {
            viewModel.deletar(contato)
            findNavController().popBackStack()
        }

        binding.buttonSalvar.setOnClickListener {
            contato.nome = binding.editTextNome.text.toString().trim()
            contato.telefone = binding.editTextTelefone.text.toString().trim()
            contato.idade = binding.editTextIdade.text.toString().trim().toInt()

            if (args.contatoId == 0)
                viewModel.inserir(contato)
            else
                viewModel.atualizar(contato)

            findNavController().popBackStack()
        }
    }
}