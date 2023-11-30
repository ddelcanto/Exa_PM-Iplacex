package com.example.evaluacion1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.evaluacion1.ui.theme.Evaluacion1Theme

class RegistrarPlanificador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegPlanUI()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegPlanUI(){

    val contexto = LocalContext.current
    val (nombreLocacion, setNombreLocacion) = remember { mutableStateOf("") }
    val (orden, setOrden) = remember { mutableStateOf("") }
    val (costoTraslado, setCostoTraslado) = remember { mutableStateOf("") }
    val (costoAlojamiento, setCostoAlojamiento) = remember { mutableStateOf("") }
    val (coordenadas, setCoordenadas) = remember { mutableStateOf("") }
    val (uriImagen, setUriImagen) = remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }

    Column(

        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Divider(
            color = Color.Gray,
            thickness = 2.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        Text(
            text = "Registrar nuevo lugar",
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        )
        Spacer(modifier =  Modifier.height(10.dp))
        Divider(
                color = Color.Gray,
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = nombreLocacion,
            onValueChange = setNombreLocacion,
            label = { Text(text = "Nombre ubicacion") }
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = orden,
            onValueChange = setOrden,
            label = { Text(text = "Orden de visita") }
        )
        Spacer(modifier =  Modifier.height(10.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = costoAlojamiento,
            onValueChange = setCostoAlojamiento,
            label = { Text(text = "Costo alojamiento") }
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = costoTraslado,
            onValueChange = setCostoTraslado,
            label = { Text(text = "Costo traslado") }
        )
        Spacer(modifier =  Modifier.height(10.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = coordenadas,
            onValueChange = setCoordenadas,
            readOnly = true,
            label = { Text(text = "") }
        )
        Spacer(modifier =  Modifier.height(5.dp))
        Button(onClick={

        }){
            Text(text ="Buscar ubicación en el mapa.")
        }
        Spacer(modifier =  Modifier.height(10.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        TextField(
            value = uriImagen,
            readOnly = true,
            onValueChange = setUriImagen,
            label = { Text(text = "") }
        )
        Spacer(modifier =  Modifier.height(5.dp))
        Button(onClick={

        }){
            Text(text ="Capturar foto")
        }
        Spacer(modifier =  Modifier.height(5.dp))
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier =  Modifier.height(10.dp))
        OutlinedTextField(
            value = comentario,
            onValueChange = { newText -> comentario = newText },
            label = { Text("Ingresa tu texto aquí") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
            maxLines = 20
        )
        Button(onClick={

        }){
            Text(text ="Ver lista de lugares a visitar")
        }

    }
}