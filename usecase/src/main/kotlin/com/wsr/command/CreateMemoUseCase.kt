package com.wsr.command

import com.wsr.MemoUseCaseModel
import com.wsr.di.DefaultDispatcher
import com.wsr.memo.Memo
import com.wsr.memo.MemoRepository
import com.wsr.memo.MemoTitle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.launch

class CreateMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(title: MemoTitle) =
        withContext(dispatcher) {
            val newMemo = Memo.create(title)
            launch { memoRepository.upsert(newMemo) }
            MemoUseCaseModel.from(newMemo)
        }
}
