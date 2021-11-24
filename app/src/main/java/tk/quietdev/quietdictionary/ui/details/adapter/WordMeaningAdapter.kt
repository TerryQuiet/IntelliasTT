package tk.quietdev.quietdictionary.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.quietdev.core.domain.models.Meaning
import tk.quietdev.quietdictionary.databinding.ItemWordMeaningBinding

class WordMeaningAdapter : ListAdapter<Meaning, WordMeaningHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordMeaningHolder {
        return WordMeaningHolder(ItemWordMeaningBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: WordMeaningHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class WordMeaningHolder(private val binding: ItemWordMeaningBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(meaning: Meaning) {
        binding.apply {
            tvPartOfSpeech.text = meaning.partOfSpeech
            tvDefinition.text = meaning.definitions.reduce { acc, s -> " $acc \n \n $s" }
        }
    }
}

object DiffCallBack : DiffUtil.ItemCallback<Meaning>() {
    override fun areItemsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
        return oldItem.partOfSpeech == newItem.partOfSpeech
    }

    override fun areContentsTheSame(oldItem: Meaning, newItem: Meaning): Boolean {
        return oldItem == newItem
    }
}