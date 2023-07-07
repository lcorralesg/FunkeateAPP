package com.corrales.luis.funkeateapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.bumptech.glide.Glide
import com.corrales.luis.funkeateapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.corrales.luis.funkeateapp.databinding.FragmentPerfilBinding
import com.corrales.luis.funkeateapp.ui.viewmodel.PerfilViewModel

//Se controlara el perfil del usuario y el inicio de sesion, registro y cerrar sesion con Auth0

class PerfilFragment : BaseFragment<FragmentPerfilBinding>(FragmentPerfilBinding::inflate) {
    private lateinit var account: Auth0
    private val perfilViewModel: PerfilViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        account = Auth0(
            "5cHBocd4hrfl8siO8W1b7jpqueMlmEtX",
            "dev-hfm58bxg2683jzf2.us.auth0.com"
        )
        initializeButtons()
    }

    private fun initializeButtons() {
        binding.loginButton.setOnClickListener {
            login()
        }
        binding.logoutButton.setOnClickListener {
            logout()
        }
    }
    private fun login() {
        val account = Auth0(requireContext())
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email read:current_user update:current_user_metadata")
            .withAudience("https://dev-hfm58bxg2683jzf2.us.auth0.com/api/v2/")
            // Launch the authentication passing the callback where the results will be received
            .start(requireActivity(), object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                }

                override fun onSuccess(result: Credentials) {
                    val accessToken = result.accessToken
                    CoroutineScope(Dispatchers.Main).launch {
                        showUserProfile(accessToken)
                        val client = AuthenticationAPIClient(account)
                        try {
                            val profile = withContext(Dispatchers.IO) {
                                client.userInfo(accessToken).execute()
                            }
                        } catch (e: Exception) {
                            // Handle profile retrieval failure
                        }
                    }
                }
            })
    }
    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(requireActivity(), object : Callback<Void?, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                }

                override fun onSuccess(result: Void?) {
                    CoroutineScope(Dispatchers.Main).launch {
                        binding.userEmail.text = ""
                        binding.userName.text = ""
                        Glide
                            .with(requireContext())
                            .load(R.drawable.funkeate_logo)
                            .into(binding.imageView3)
                        // Cambiar el estado de visibilidad de los botones

                        binding.loginButton.visibility = View.VISIBLE
                        binding.registerButton.visibility = View.VISIBLE
                        binding.logoutButton.visibility = View.GONE

                        // Cambiar el estado de visibilidad de los datos del usuario
                        binding.userDataLayout.visibility = View.GONE
                        binding.textView4.visibility = View.VISIBLE

                    }
                }
            })
    }
    private fun showUserProfile(accessToken: String) {
        val client = AuthenticationAPIClient(account)
        client.userInfo(accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {

                }
                override fun onSuccess(result: UserProfile) {
                    binding.userEmail.text = "Email: ${result.email}"
                    binding.userName.text = "Nombre de usuario: ${result.name}"
                    Glide
                        .with(requireContext())
                        .load(result.pictureURL)
                        .into(binding.imageView3)

                    // Cambiar el estado de visibilidad de los botones
                    binding.loginButton.visibility = View.GONE
                    binding.registerButton.visibility = View.GONE
                    binding.logoutButton.visibility = View.VISIBLE
                    // Cambiar el estado de visibilidad de los datos del usuario
                    binding.userDataLayout.visibility = View.VISIBLE
                    binding.textView4.visibility = View.GONE
                }
            })
    }
}
