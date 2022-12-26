package com.wsr.ui.memo.show.screen

import android.content.Intent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun MemoShowTopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    memoTitle: String,
    createSharedText: () -> String,
    deleteCheckedItems: () -> Unit,
) {
    val context = LocalContext.current

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = memoTitle,
                style = MaterialTheme.typography.h5,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, createSharedText())
                }
                context.startActivity(intent)
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                )

            }
            IconButton(onClick = deleteCheckedItems) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                )
            }
        },
    )
}
