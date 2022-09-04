package com.wsr

import com.wsr.FetchMemoByIdItemUseCaseModel.Companion.toGetMemoByIdItemUseCaseModel
import com.wsr.FetchMemoByIdUseCaseModel.Companion.toGetMemoByIdUseCaseModel
import com.wsr.di.DefaultDispatcher
import com.wsr.exception.DomainException
import com.wsr.memo.Item
import com.wsr.memo.ItemContent
import com.wsr.memo.ItemId
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

class GetMemoByIdUseCase @Inject constructor(
    private val memoRepository: MemoRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

    suspend operator fun invoke(memoId: MemoId) = withContext(dispatcher) {
        memoRepository.getById(memoId).map { it.toGetMemoByIdUseCaseModel() }
    }
}

interface FetchMemoByIdQueryService {
    val flow: Flow<ApiResult<Memo, DomainException>>

    suspend operator fun invoke(memoId: MemoId)
}

data class FetchMemoByIdUseCaseModel(
    val id: MemoId,
    val title: MemoTitle,
    val items: List<FetchMemoByIdItemUseCaseModel>,
) {
    companion object {
        fun Memo.toGetMemoByIdUseCaseModel() = FetchMemoByIdUseCaseModel(
            id = id,
            title = title,
            items = items.map { it.toGetMemoByIdItemUseCaseModel() },
        )
    }
}

data class FetchMemoByIdItemUseCaseModel(
    val id: ItemId,
    val checked: Boolean,
    val content: ItemContent,
) {
    companion object {
        fun Item.toGetMemoByIdItemUseCaseModel() = FetchMemoByIdItemUseCaseModel(
            id = id,
            checked = checked,
            content = content,
        )
    }
}