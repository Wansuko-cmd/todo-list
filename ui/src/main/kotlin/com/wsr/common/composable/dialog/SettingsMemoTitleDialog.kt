package com.wsr.common.composable.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wsr.theme.HoloBlueDark
import com.wsr.theme.MossGreen
import com.wsr.theme.TodoListTheme
import com.wsr.ui.R

@Composable
fun SettingsMemoTitleDialog(
    modifier: Modifier = Modifier,
    initialValue: String = "",
    onDismiss: () -> Unit,
    onConfirm: (title: String) -> Unit,
) {
    val (text, setText) = remember { mutableStateOf(initialValue) }
    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(
                modifier = modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 16.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.dialog_settings_memo_title_title),
                    style = MaterialTheme.typography.h5,
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = setText,
                    placeholder = { Text(text = stringResource(id = R.string.dialog_settings_memo_title_placeholder)) },
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Button(
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MossGreen,
                        ),
                        shape = RoundedCornerShape(5.dp),
                        onClick = onDismiss,
                    ) {
                        Text(
                            color = Color.White,
                            text = stringResource(id = R.string.dialog_settings_memo_title_dismiss_button),
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = HoloBlueDark,
                        ),
                        shape = RoundedCornerShape(5.dp),
                        onClick = { onConfirm(text) },
                    ) {
                        Text(
                            color = Color.White,
                            text = stringResource(id = R.string.dialog_settings_memo_title_confirm_button),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun MemoIndexCreateMemoDialogPreview() {
    TodoListTheme {
        SettingsMemoTitleDialog(
            onDismiss = {},
            onConfirm = {},
        )
    }
}
