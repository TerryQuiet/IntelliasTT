package tk.quietdev.quietdictionary.ui.details

import tk.quietdev.quietdictionary.base.BaseFragment
import tk.quietdev.quietdictionary.databinding.WordDetailsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordDetailsFragment : BaseFragment<WordDetailsFragmentBinding>(WordDetailsFragmentBinding::inflate) {
    val viewModel: WordDetailsViewModel by viewModel()

}