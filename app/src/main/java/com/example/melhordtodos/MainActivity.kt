package com.example.melhordtodos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.melhordtodos.ui.theme.MelhorDtodosTheme
import android.widget.Toast

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MelhorDtodosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginCompose { user ->
                        Log.i("MainActivity", "Usuário: ${user.nome}, Senha: ${user.password}")

                        // Navega para a tela do Melhor de Todos
                        val intent = Intent(this, MelhorDeTodosActivity::class.java)
                        intent.putExtra("USER_NAME", user.nome)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginCompose(onEnterClick: (User) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), // padding lateral
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            lineHeight = 40.sp,
            text = "Bem vindo ao Melhor de Todos"
        )

        TextField(
            value = username, onValueChange = { newValue ->
                username = newValue
            }, Modifier
                .padding(8.dp)
                .fillMaxWidth(), label = {
                Text("Usuário", fontSize = 20.sp)
            }, leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuário"
                )
            })

        TextField(
            value = password, onValueChange = {
                password = it
            }, Modifier
                .padding(8.dp)
                .fillMaxWidth(), label = {
                Text("Senha", fontSize = 20.sp)
            }, visualTransformation = PasswordVisualTransformation(), leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Senha"
                )
            })

        ElevatedButton(
            onClick = {
                val user = User(username, password)
                onEnterClick(user)
            }, Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White
            )
        ) {
            Text("Entrar", fontSize = 20.sp)
        }
        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White
            )
        ){
            Text("Criar conta", fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
fun LoginComposePreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        LoginCompose { Log.i("Preview", "Usuário: ${it.nome}, Senha: ${it.password}") }
    }
}