package tk.quietdev.core.usecase

import kotlinx.coroutines.flow.Flow
import tk.quietdev.core.data.Repository

class FlowCachedWordsUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<List<String>> {
        return repository.flowCachedWords()
    }
}