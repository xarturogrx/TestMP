package com.arturogr.mytestmp.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.arturogr.mytestmp.presentation.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: Int,
    onBack:() -> Unit = {},
    upAvailable: Boolean = false,
    isLogout: Boolean = false,
    onLogout: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = title),
                fontSize = 18.sp,
                color = Color.White
            )
        },
        actions = {
                  if (isLogout) {
                      IconButton(onClick = onLogout ) {
                          Icon(
                              imageVector =  Icons.AutoMirrored.Filled.Logout,
                              contentDescription = "TopBar",
                              tint = Color.White
                          )
                      }
                  }
        },
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = onBack ) {
                    Icon(
                        imageVector =  Icons.Filled.ArrowBackIosNew,
                        contentDescription = "TopBar",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple40)
    )
}