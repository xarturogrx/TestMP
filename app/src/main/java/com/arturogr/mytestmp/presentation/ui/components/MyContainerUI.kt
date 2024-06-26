package com.arturogr.mytestmp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arturogr.mytestmp.R

@Composable
fun MyContainerUI(
    isToolbar: Boolean = false,
    titleTollBar: Int = R.string.app_name,
    isLogout: Boolean = false,
    onBack: () -> Unit = {},
    onLogout: () -> Unit = {},
    upAvailable: Boolean = false,
    content: @Composable () -> Unit = {},
) {
    Scaffold(
        topBar = {
            if (isToolbar) {
                ToolBar(titleTollBar, onBack, upAvailable, isLogout, onLogout)
            }
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                Modifier
                    .padding(12.dp)
                    .fillMaxHeight()
                    .wrapContentHeight()
            ) {
                content.invoke()
            }
        }
    }
}