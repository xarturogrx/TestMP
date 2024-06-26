package com.arturogr.mytestmp.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MySpacerUI(top: Dp = 12.dp) {
    Spacer(modifier = Modifier.fillMaxWidth().padding(top = top))
}