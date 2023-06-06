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
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var account: Auth0
    private lateinit var btnLogin: Button
    private lateinit var btnLogout: Button
    private lateinit var txtNombre_producto: TextView
    private lateinit var txtCategoria: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtPrecio: TextView
    private lateinit var txtNombre_producto2: TextView
    private lateinit var txtCategoria2: TextView
    private lateinit var txtDescripcion2: TextView
    private lateinit var txtPrecio2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up the account object with the Auth0 application details
        account = Auth0(
            "nvyWbZeudGH07w6Ybo5iDKw197sZQ0ns",
            "dev-gb61hjary7ea583j.us.auth0.com"
        )
        setContentView(R.layout.activity_main)
        // Al presionar el botón loginWithBrowser se ejecuta la función loginWithBrowser
        btnLogin = findViewById(R.id.btnLogin)
        btnLogout = findViewById(R.id.btnLogout)
        txtNombre_producto = findViewById(R.id.txtNombre_producto)
        txtCategoria = findViewById(R.id.txtCategoria)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtNombre_producto2 = findViewById(R.id.txtNombre_producto2)
        txtCategoria2 = findViewById(R.id.txtCategoria2)
        txtDescripcion2 = findViewById(R.id.txtDescripcion2)
        txtPrecio2 = findViewById(R.id.txtPrecio2)


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
            .withAudience("https://dev-gb61hjary7ea583j.us.auth0.com/api/v2/")
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
                    CoroutineScope(Dispatchers.Main).launch {
                        getAllProducts(accessToken)
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
            .start(this, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    // The user has been logged out!
                    runOnUiThread {
                        txtNombre_producto.text = ""
                        txtCategoria.text = ""
                        txtDescripcion.text = ""
                        txtPrecio.text = ""
                        txtNombre_producto2.text = ""
                        txtCategoria2.text = ""
                        txtDescripcion2.text = ""
                        txtPrecio2.text = ""
                    }
                }

                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!
                }
            })
    }

    private fun showUserProfile(accessToken: String) {
        val client = AuthenticationAPIClient(account)

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
                }
            })
    }

    private fun getUserMetadata(userId: String, accessToken: String) {
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
                }
            })
    }

    private fun getAllProducts(accessToken: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://funkeateapi-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("Authorization", "Bearer $accessToken").build()
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
                        withContext(Dispatchers.Main) {
                            txtNombre_producto.text = it[0].nombre
                            txtCategoria.text = it[0].categoria.nombre
                            txtDescripcion.text = it[0].descripcion
                            txtPrecio.text = it[0].precio.toString()
                            txtNombre_producto2.text = it[1].nombre
                            txtCategoria2.text = it[1].categoria.nombre
                            txtDescripcion2.text = it[1].descripcion
                            txtPrecio2.text = it[1].precio.toString()
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