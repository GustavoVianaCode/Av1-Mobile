package com.example.melhordtodos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.background
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.melhordtodos.ui.theme.MelhorDtodosTheme

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
            .background(
                brush = Brush.run {
                    verticalGradient(
                                colors = listOf(
                                    Color(0xFF010814),
                                    Color(0xFF02173B)
                                )
                            )
                }
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            lineHeight = 40.sp,
            text = "Bem vindo ao Melhor de Todos",
            color = Color(0xFFFFFFFF)
        )

        OutlinedTextField(
            value = username,
            onValueChange = { newValue ->
                username = newValue
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = {
                Text("Usuário", fontSize = 20.sp)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuário"
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF89CFF0),  // Azul do gradiente
                unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                focusedLabelColor = Color(0xFF89CFF0),
                unfocusedLabelColor = Color.White.copy(alpha = 0.8f),
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                cursorColor = Color(0xFF89CFF0)
            ),
            shape = RoundedCornerShape(12.dp)  // Bordas arredondadas
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = {
                Text("Senha", fontSize = 20.sp)
            },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Senha"
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color(0xFFFFB347),  // Laranja do gradiente
                unfocusedBorderColor = Color.White.copy(alpha = 0.7f),
                focusedLabelColor = Color(0xFFFFB347),
                unfocusedLabelColor = Color.White.copy(alpha = 0.8f),
                focusedTextColor = Color(0xFF333333),
                unfocusedTextColor = Color(0xFF333333),
                cursorColor = Color(0xFFFFB347)
            ),
            shape = RoundedCornerShape(12.dp)
        )

        ElevatedButton(
            onClick = {
                val user = User(username, password)
                onEnterClick(user)
            }, Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color(0xFF335CDE),
                contentColor = Color(color = 0xFFFFFFFF)
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
                containerColor = Color(0xFF335CDE),
                contentColor = Color(color = 0xFFFFFFFF)
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