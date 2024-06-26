package com.arturogr.mytestmp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyButtonUI(text: String,enable: Boolean, onClick: () -> Unit = {}) {
    Button(modifier = Modifier.fillMaxWidth(), enabled = enable, onClick = { onClick.invoke() }) {
        Text(text = text)
    }
}