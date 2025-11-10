package com.example.melhordtodos

//import com.example.melhordtodos.ui.theme.MelhorDtodosTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.material3.ButtonDefaults
import com.example.melhordtodos.ui.theme.MelhorDtodosTheme
import kotlin.random.Random


class MelhorDeTodosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userName = intent.getStringExtra("USER_NAME") ?: "Usuário"

        setContent {
            MelhorDtodosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Milhor(userName = userName)
                }
            }
        }
    }
}

@Composable
fun Milhor(modifier: Modifier = Modifier, userName: String) {
    val melhores = listOf(
        "Ale",
        "Ally",
        "Cesar",
        "Fazl",
        "Guilherme",
        "Gusta",
        "Dragonite",
        "Iranildo",
        "JP",
        "Luan",
        "Nariz",
        "Pablo",
        "Rai",
        "Rodrigo",
        "Samu"
    )

    val imagens = mapOf(
        "Ale" to R.drawable.ale,
        "Ally" to R.drawable.ally,
        "Cesar" to R.drawable.cesar,
        "Fazl" to R.drawable.fazl,
        "Guilherme" to R.drawable.guilherme,
        "Gusta" to R.drawable.gusta,
        "Dragonite" to R.drawable.dragonite,
        "Iranildo" to R.drawable.iranildo,
        "JP" to R.drawable.jp,
        "Luan" to R.drawable.luan,
        "Nariz" to R.drawable.nariz,
        "Pablo" to R.drawable.pablo,
        "Rai" to R.drawable.rai,
        "Rodrigo" to R.drawable.rodrigo,
        "Samu" to R.drawable.samu
    )

    var sorteado by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF010814),
                        Color(0xFF02173B)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Salve, $userName!",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFFE9EDFB),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Guilherme se aposentou para tocar o Lápis 2",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFE9EDFB),
            style = MaterialTheme.typography.titleMedium,
            lineHeight = 22.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Clique no botão para descobrir quem é o novo Melhor de Todos.",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFE9EDFB),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val id = Random.nextInt(melhores.size)
                sorteado = melhores[id]
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF335CDE),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "E o novo G.U.I.L.H.E.R.M.E é?",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        sorteado?.let { melhorAtual ->
            val imagemRes = imagens[melhorAtual]
            if (imagemRes != null) {
                Image(
                    painter = painterResource(id = imagemRes),
                    contentDescription = "Foto de $melhorAtual",
                    modifier = Modifier
                        .size(340.dp)
                        .padding(top = 20.dp, bottom = 12.dp)
                )
            }

            val texto = if (melhorAtual == "Dragonite")
                "Isso é o melhor? $melhorAtual."
            else if (melhorAtual == "Pablo")
                "Esse é o maior VACILÃO, $melhorAtual."
            else
                "Melhor de Todos: $melhorAtual."

            Text(
                text = texto,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color(0xFFE9EDFB),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MilhorPreview() {
    MelhorDtodosTheme {
        Milhor(userName = "Usuário Teste")
    }
}