package com.corrales.luis.funkeateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.corrales.luis.funkeateapp.data.network.ProductService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var account: Auth0
    private lateinit var btnLogin: Button
    private lateinit var btnLogout: Button
    private lateinit var txtEmail: TextView
    private lateinit var txtName: TextView
    private lateinit var txtPictureURL: TextView
    private lateinit var txtNickname: TextView
    private lateinit var txtCountry: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up the account object with the Auth0 application details
        account = Auth0(
            "5cHBocd4hrfl8siO8W1b7jpqueMlmEtX",
            "dev-hfm58bxg2683jzf2.us.auth0.com"
        )
        setContentView(R.layout.activity_main)
        // Al presionar el botón loginWithBrowser se ejecuta la función loginWithBrowser
        btnLogin = findViewById(R.id.btnLogin)
        btnLogout = findViewById(R.id.btnLogout)
        txtEmail = findViewById(R.id.txtEmail)
        txtName = findViewById(R.id.txtName)
        txtPictureURL = findViewById(R.id.txtPictureURL)
        txtNickname = findViewById(R.id.txtNickname)
        txtCountry = findViewById(R.id.txtCountry)

        btnLogin.setOnClickListener {
            loginWithBrowser()
        }
        btnLogout.setOnClickListener {
            logout()
        }

    }
    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.

        WebAuthProvider.login(account)
            .withScheme("demo")
            // modify the scopes to enable read and write of user_metadata
            .withScope("openid profile email read:current_user update:current_user_metadata")
            // specify the audience for the Auth0 Management API
            .withAudience("https://dev-hfm58bxg2683jzf2.us.auth0.com/api/v2/")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val accessToken = credentials.accessToken
                    getAllProducts(accessToken)
                    showUserProfile(accessToken)
                    val client = AuthenticationAPIClient(account)
                    client.userInfo(accessToken)
                        .start(object : Callback<UserProfile, AuthenticationException> {
                            override fun onFailure(exception: AuthenticationException) {
                                // Manejar el error
                            }

                            override fun onSuccess(profile: UserProfile) {
                                val userId = profile.getId()
                                if (userId != null) {
                                    getUserMetadata(userId, accessToken)
                                }
                            }
                        })

                }
            })
    }
    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(this, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    // The user has been logged out!
                    txtEmail.text = ""
                    txtName.text = ""
                    txtPictureURL.text = ""
                    txtNickname.text = ""
                    txtCountry.text = ""
                }

                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!

                }
            })
    }
    private fun showUserProfile(accessToken: String) {
        var client = AuthenticationAPIClient(account)

        // With the access token, call `userInfo` and get the profile from Auth0.
        client.userInfo(accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                }

                override fun onSuccess(profile: UserProfile) {
                    // We have the user's profile!
                    val email = profile.email
                    val name = profile.name
                    val pictureURL = profile.pictureURL
                    val nickname = profile.nickname

                    txtEmail.text = email
                    txtName.text = name
                    txtPictureURL.text = pictureURL
                    txtNickname.text = nickname

                }
            })
    }
    fun getUserMetadata(userId: String, accessToken: String) {
        // Get the user ID and call the full getUser Management API endpoint, to retrieve the full profile information
        // Create the user API client using the account details and the access token from Credentials
        val usersClient = UsersAPIClient(account, accessToken)

        // Get the full user profile
        usersClient
            .getProfile(userId)
            .start(object: Callback<UserProfile, ManagementException> {
                override fun onFailure(exception: ManagementException) {
                    // Something went wrong!
                }

                override fun onSuccess(profile: UserProfile) {
                    // Retrieve the "country" field, if one appears in the metadata
                    val country = profile.getUserMetadata()["country"] as String?
                    txtCountry.text = country
                }
            })
    }

    fun getAllProducts(accessToken: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.3:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer ${accessToken}").build()
                chain.proceed(request)
            }.build())
            .build()
        val productService = retrofit.create(ProductService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = productService.getAllProducts()
                if (response.isSuccessful) {
                    val productsResponse = response.body()
                    val productList = productsResponse?.data

                    productList?.let {
                        for (product in productList) {
                            println(product.id)
                            println(product.nombre)
                            println(product.precio)
                            println(product.descripcion)
                            println(product.categoria)
                            println(product.categoria.nombre)
                        }
                    }
                } else {
                    println(response.code())
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}