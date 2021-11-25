package tk.quietdev.quietdictionary.ui.search

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import tk.quietdev.level1.common.Resource
import tk.quietdev.quietdictionary.R
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
                    openWordDetails(it.data!!.word)
                }
                is Resource.Error -> {
                    it.message?.let { showErrorSnackbar(it) }
                }
                else -> { /*loading?*/ }
            }
        }

        viewModel.cachedWords.observe(viewLifecycleOwner) {
                drawCachedWords(it)
        }
    }

    private fun drawCachedWords(it: List<String>) {
        for (child in binding.cLayout.children) {
            if (child is AppCompatTextView) {
                binding.cLayout.removeView(child)
                binding.flow.removeView(child)
            }
        }

        it.forEach { word ->
            val wordView = LayoutInflater.from(context).inflate(
                R.layout.item,
                binding.cLayout,
                false
            ).apply {
                id = View.generateViewId()
            }

            binding.cLayout.addView(wordView)
            binding.flow.addView(wordView)

            (wordView as AppCompatTextView).text = word
            wordView.setOnClickListener {
                openWordDetails(word)
            }
        }
    }

    private fun openWordDetails(word: String) {
        findNavController().navigate(
            WordSearchFragmentDirections.actionLoginFragmentToRegistrationFragment(
                word
            )
        )
        viewModel.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.wtf("TAG", "onDestroyView: ")
    }

    private fun showErrorSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .show()
    }

}