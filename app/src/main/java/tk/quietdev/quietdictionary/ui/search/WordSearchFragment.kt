package tk.quietdev.quietdictionary.ui.search

import android.util.Log
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.base.BaseFragment
import tk.quietdev.quietdictionary.databinding.WordSearchFragmentBinding

class WordSearchFragment :
    BaseFragment<WordSearchFragmentBinding>(WordSearchFragmentBinding::inflate) {
    private val viewModel: WordSearchViewModel by viewModel()

    override fun setListeners() {
        super.setListeners()
        binding.btnSearch.setOnClickListener {
            viewModel.getHello(binding.etSearch.text.toString())
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.currentWord.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    Log.d("TAG", "setObservers: ")
                    //openWordDetails(it.data!!.word)
                    binding.tvResponse.text =
                        it.data?.meanings?.first()?.definitions?.first()
                }
                is Resource.Error -> binding.tvResponse.text = it.message
                else -> {}
            }
        }

        viewModel.cachedWords.observe(viewLifecycleOwner) {
            binding.tvResponse.text = it.reduce { acc, s -> " $acc  \n  $s" }
        }
    }

    private fun openWordDetails(word: String) {
        findNavController().navigate(WordSearchFragmentDirections.actionLoginFragmentToRegistrationFragment(word))
        viewModel.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.wtf("TAG", "onDestroyView: ", )
    }

}