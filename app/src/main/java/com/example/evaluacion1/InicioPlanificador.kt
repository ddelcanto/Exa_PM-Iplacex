package com.example.evaluacion1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class InicioPlanificador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanUI(){

    val contexto =  LocalContext.current

    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        Image(
            painter = painterResource(id = R.drawable.ruta),
            contentDescription = "",
            modifier = Modifier
                .size(300.dp),
            contentScale = ContentScale.Inside
        )
        Spacer(modifier =  Modifier.height(30.dp))
        Button(onClick={
            contexto.startActivity(Intent(contexto, RegistrarPlanificador::class.java))

            val intent = Intent(contexto, RegistrarPlanificador::class.java)
            intent.putExtra("nombre", "Sin datos")
            intent.putExtra("latitud", "4121212")
            intent.putExtra("longitud","Sin datos")
            contexto.startActivity(intent)
        }){
            Text(text ="Registrar lugar a visitar")
        }
        Spacer(modifier =  Modifier.height(30.dp))
        Button(onClick={

        }){
            Text(text ="Ver lista de lugares a visitar")
        }

    }
}