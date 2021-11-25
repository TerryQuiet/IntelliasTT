package tk.quietdev.quietdictionary.ui.search

import android.graphics.Color
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import tk.quietdev.quietdictionary.R
import tk.quietdev.quietdictionary.base.BaseFragment
import tk.quietdev.quietdictionary.databinding.WordSearchFragmentBinding
import tk.quietdev.quietdictionary.util.hideSoftKeyboard

class WordSearchFragment :
    BaseFragment<WordSearchFragmentBinding>(WordSearchFragmentBinding::inflate) {
    private val viewModel: WordSearchViewModel by viewModel()

    override fun setListeners() {
        super.setListeners()
        binding.btnSearch.setOnClickListener {
            viewModel.getWord(binding.etSearch.text.toString())
        }

        binding.etSearch.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    requireActivity().hideSoftKeyboard()
                    viewModel.getWord(binding.etSearch.text.toString())
                    return true
                }
                return false
            }
        })

    }

    override fun setObservers() {
        super.setObservers()

        viewModel.cachedWords.observe(viewLifecycleOwner) {
            drawCachedWords(it)
        }

        viewModel.eventsFlow
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            )
            .onEach {
                when (it) {
                    is WordSearchViewModel.Event.NavigateToDetails -> {
                        openWordDetails(it.text)
                    }
                    is WordSearchViewModel.Event.ShowSnackBar -> {
                        showErrorSnackbar(it.text)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

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
    }

    private fun showErrorSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setTextColor(Color.WHITE)
            .show()
    }

}