package com.wsr.command.element

import com.wsr.MemoUseCaseModel
import com.wsr.di.DefaultDispatcher
import com.wsr.memo.MemoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DeleteCheckedItemsUseCase @Inject constructor(
    private val memoRepository: MemoRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(memo: MemoUseCaseModel): MemoUseCaseModel =
        updateMemoAndReturn(memo, memoRepository, dispatcher) { it.deleteCheckedItems() }
}
