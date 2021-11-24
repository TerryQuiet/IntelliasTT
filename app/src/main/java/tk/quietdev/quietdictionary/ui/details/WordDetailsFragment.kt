package tk.quietdev.quietdictionary.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import tk.quietdev.quietdictionary.base.BaseFragment
import tk.quietdev.quietdictionary.databinding.WordDetailsFragmentBinding
import tk.quietdev.quietdictionary.ui.ToolbarConfigure
import tk.quietdev.quietdictionary.ui.details.adapter.WordMeaningAdapter

class WordDetailsFragment : BaseFragment<WordDetailsFragmentBinding>(WordDetailsFragmentBinding::inflate) {
    private val args: WordDetailsFragmentArgs by navArgs()
    private val viewModel: WordDetailsViewModel by viewModel{ parametersOf(args.word)}

    private val wordMeaningAdapter: WordMeaningAdapter by lazy { WordMeaningAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as ToolbarConfigure).changeToolBarTitle(args.word)
        initRecycleView()
    }

    private fun initRecycleView() {
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = wordMeaningAdapter
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.currentWord.observe(viewLifecycleOwner) {
            binding.tvPhonetic.text = "[${it.phonetic}]"
            wordMeaningAdapter.submitList(it.meanings)
        }
    }

}