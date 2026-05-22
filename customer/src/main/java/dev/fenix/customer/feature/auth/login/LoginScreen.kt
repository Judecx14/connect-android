package dev.fenix.customer.feature.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun LoginScreen() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
                    .weight(0.4f),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(text = "Bienvenido a Connect")
                Text(text = "Ingresa tus credenciales para acceder a la aplicaicon o usa un metodo de autenticacion.")
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .weight(0.6f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {
                    Column {
                        Text(text = "Email")
                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Escribe tu email") }
                        )
                    }

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Password")
                            TextButton(onClick = {}) {
                                Text(text = "Recuperar")
                            }
                        }
                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Escribe tu password") }
                        )
                    }

                    Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Login")
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 64.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {}) {
                            Text(text = "G")
                        }

                        Text(text = "Or")

                        Button(onClick = {}) {
                            Text(text = "A")
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "No tienes cuenta?")
                    TextButton(onClick = {}) {
                        Text(text = "Registrarse")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    ConnectTheme {
        LoginScreen()
    }
}

