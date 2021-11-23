package tk.quietdev.quietdictionary.ui.search


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import org.koin.androidx.viewmodel.ext.android.viewModel
import tk.quietdev.quietdictionary.base.BaseFragment
import tk.quietdev.quietdictionary.databinding.WordSearchFragmentBinding

class WordSearchFragment :
    BaseFragment<WordSearchFragmentBinding>(WordSearchFragmentBinding::inflate) {
    private val viewModel: WordSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.INTERNET)
            == PackageManager.PERMISSION_GRANTED) {
            Log.wtf("TAG", "onViewCreated: YES", )
        } else {
            Log.wtf("TAG", "onViewCreated: NOT", )
        }

        Log.wtf("TAG", "onViewCreated: pre", )

        Log.wtf("TAG", "onViewCreated: after", )
    }

    override fun setListeners() {
        super.setListeners()
        binding.find.setOnClickListener {
            viewModel.getHello(binding.etSearch.text.toString())
        }
    }

}