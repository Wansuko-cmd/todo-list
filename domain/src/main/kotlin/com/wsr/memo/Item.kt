package com.wsr.memo

data class Item(
    val id: ItemId,
    val checked: Boolean,
    val content: ItemContent,
)

@JvmInline
value class ItemId(val value: String)

@JvmInline
value class ItemContent(val value: String)
