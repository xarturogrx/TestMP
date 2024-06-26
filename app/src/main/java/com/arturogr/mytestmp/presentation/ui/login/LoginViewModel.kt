package com.arturogr.mytestmp.presentation.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arturogr.mytestmp.core.StateView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _stateView = MutableLiveData<String>()
    val stateView: LiveData<String> = _stateView

        private val _errorMsn = MutableLiveData<String>()
    val errorMsn: LiveData<String> = _errorMsn


    private val _isEnableButton = MutableLiveData<Boolean>()
    val isEnableButton: LiveData<Boolean> = _isEnableButton

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    fun signedIn() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setStateView(stateView = StateView.Success.stateView)
        }
    }

    fun onValueChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        validateButton(_email.value.toString(), _password.value.toString())
    }

    private fun validateButton(email: String, password: String) {
        _isEnableButton.value =
            Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

    fun signIn(email: String, password: String) {
        setStateView(stateView = StateView.Loading.stateView)
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                setStateView(stateView = StateView.Success.stateView)
            } else {
                setStateView(stateView = StateView.Error.stateView)
                _errorMsn.value = task.exception?.message ?: "Error"
            }
        }
    }

    fun setStateView(stateView: String) {
        _stateView.value = stateView
    }
}