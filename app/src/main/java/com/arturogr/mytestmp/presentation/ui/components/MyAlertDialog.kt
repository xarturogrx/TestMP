package com.arturogr.mytestmp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arturogr.mytestmp.R


@Composable
fun MyAlertDialog(
    isShow: Boolean,
    text: String,
    icon: Painter,
    isCancel: Boolean = false,
    onCancel: () -> Unit = {},
    onConfirm: () -> Unit = {}
) {
    if (isShow) {
        AlertDialog(
            onDismissRequest = { },
            confirmButton = { },
            title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Image(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        painter = icon,
                        contentDescription = "AlertDialog",
                    )
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = text,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(24.dp),
                    )
                    HorizontalDivider()
                    Row(Modifier.align(Alignment.End)) {
                        if (isCancel) {
                            TextButton(
                                onClick = { onCancel.invoke() },
                                modifier = Modifier.padding(top = 24.dp)
                            ) {
                                Text(text = stringResource(id = R.string.cancel))
                            }
                        }
                        TextButton(
                            onClick = { onConfirm.invoke() },
                            modifier = Modifier.padding(top = 24.dp)
                        ) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    }
                }

            },
            modifier = Modifier
                .padding(top = 20.dp),
        )
    }
}
