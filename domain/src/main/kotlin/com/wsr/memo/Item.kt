package com.wsr.memo

import java.util.UUID

class Item private constructor(
    val id: ItemId,
    val checked: Boolean,
    val content: ItemContent,
) {
    fun updateContent(content: ItemContent): Item = reconstruct(
        id = id,
        checked = checked,
        content = content,
    )

    fun switchChecked(): Item = reconstruct(
        id = id,
        checked = !checked,
        content = content,
    )

    companion object {
        fun create(): Item = Item(
            id = ItemId(UUID.randomUUID().toString()),
            checked = false,
            content = ItemContent(""),
        )

        fun reconstruct(
            id: ItemId,
            checked: Boolean,
            content: ItemContent,
        ): Item = Item(
            id = id,
            checked = checked,
            content = ItemContent(content.value.replace("\n", "")),
        )
    }
}

@JvmInline
value class ItemId(val value: String)

@JvmInline
value class ItemContent(val value: String)
