package tk.quietdev.core.usecase

import tk.quietdev.core.data.Repository
import tk.quietdev.core.domain.models.WordModel
import tk.quietdev.level1.common.Resource

class GetWordUseCase(private val repository: Repository) {
    suspend operator fun invoke(word: String): Resource<WordModel> {
        return repository.getWord(word)
    }
}