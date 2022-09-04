package com.wsr

import com.wsr.FetchAllMemoUseCaseModel.Companion.toGetAllMemoUseCaseModel
import com.wsr.di.DefaultDispatcher
import com.wsr.exception.DomainException
import com.wsr.memo.Memo
import com.wsr.memo.MemoId
import com.wsr.memo.MemoRepository
import com.wsr.memo.MemoTitle
import com.wsr.result.ApiResult
import com.wsr.result.map
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

    suspend operator fun invoke() = withContext(dispatcher) {
        memoRepository.getAll().map { memos -> memos.map { it.toGetAllMemoUseCaseModel() } }
    }
}

interface FetchAllMemoQueryService {
    val flow: Flow<ApiResult<List<Memo>, DomainException>>

    suspend operator fun invoke()
}

data class FetchAllMemoUseCaseModel(
    val id: MemoId,
    val title: MemoTitle,
) {
    companion object {
        fun Memo.toGetAllMemoUseCaseModel() =
            FetchAllMemoUseCaseModel(
                id = id,
                title = title,
            )
    }
}