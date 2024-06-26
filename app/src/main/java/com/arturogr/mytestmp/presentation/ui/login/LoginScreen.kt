package com.arturogr.mytestmp.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.arturogr.mytestmp.R.drawable
import com.arturogr.mytestmp.R.string
import com.arturogr.mytestmp.core.StateView
import com.arturogr.mytestmp.core.Constants
import com.arturogr.mytestmp.presentation.ui.components.MyAlertDialog
import com.arturogr.mytestmp.presentation.ui.components.MyButtonUI
import com.arturogr.mytestmp.presentation.ui.components.MyContainerUI
import com.arturogr.mytestmp.presentation.ui.components.MyOutlinedTextFieldUI
import com.arturogr.mytestmp.presentation.ui.components.MyProgressUI
import com.arturogr.mytestmp.presentation.ui.components.MySpacerUI
import com.arturogr.mytestmp.presentation.ui.components.MyTextUI

@Preview
@Composable
fun LoginScreen(onNext: () -> Unit = {}, onSignUp: () -> Unit = {}) {
    MyContainerUI {
        LoginUI(onNext = onNext, onSignUp = onSignUp)
    }
}

@Composable
fun LoginUI(viewModel: LoginViewModel = hiltViewModel(), onNext: () -> Unit = {}, onSignUp: () -> Unit = {}) {
    val stateView: String by viewModel.stateView.observeAsState(StateView.Init.stateView)
    val errorMsn: String by viewModel.errorMsn.observeAsState("")
    val isEnableButton: Boolean by viewModel.isEnableButton.observeAsState(false)
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")

    LaunchedEffect(Unit) {
        viewModel.signedIn()
    }

    when (stateView) {
        StateView.Loading.stateView -> {
            MyProgressUI()
        }

        StateView.Success.stateView -> {
            viewModel.setStateView(StateView.Init.stateView)
            onNext.invoke()
        }

        StateView.Error.stateView -> {
            MyAlertDialog(true, errorMsn,
                icon = painterResource(id = drawable.error), onConfirm = {
                    viewModel.setStateView(StateView.Init.stateView)
                })
        }
    }

    MySpacerUI(Constants.MARGIN_LARGE)
    MyTextUI(stringResource(id = string.app_name), Constants.TEXT_SIZE_LARGE)
    MySpacerUI(Constants.MARGIN_MEDIUM)
    MyOutlinedTextFieldUI(
        placeHolder = string.email,
        value = email,
        onValueChange = {
            viewModel.onValueChange(it, password)
        },
        keyboardType = KeyboardType.Email
    )
    MyOutlinedTextFieldUI(
        placeHolder = string.password,
        value = password,
        isPass = true,
        onValueChange = {
            viewModel.onValueChange(email, it)
        },
        keyboardType = KeyboardType.Password
    )

    MySpacerUI(Constants.MARGIN_MEDIUM)
    MyButtonUI(stringResource(id = string.sign_in), enable = isEnableButton,
        onClick = { viewModel.signIn(email = email, password = password) }
    )
    MySpacerUI()
    MyButtonUI(stringResource(id = string.sign_up), enable = true,
        onClick = { onSignUp.invoke() }
    )
}
