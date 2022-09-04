package com.wsr.ui.memo.show.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wsr.ui.memo.show.MemoShowItemUiState

@Composable
fun MemoShowItemTile(
    modifier: Modifier = Modifier,
    itemUiState: MemoShowItemUiState,
    onChecked: (itemId: String) -> Unit,
    onChangeContent: (itemId: String, content: String) -> Unit,
) {
    val backgroundColor = if (itemUiState.checked) Color.White else Color.Cyan

    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = backgroundColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = itemUiState.checked, onCheckedChange = { onChecked(itemUiState.id) })
            TextField(
                value = itemUiState.content,
                onValueChange = { onChangeContent(itemUiState.id, it) },
                textStyle = MaterialTheme.typography.h4,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = backgroundColor,
                    unfocusedIndicatorColor = backgroundColor,
                    focusedIndicatorColor = backgroundColor,
                )
            )
        }
    }
}
