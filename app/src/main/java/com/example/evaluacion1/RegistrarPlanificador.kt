package com.example.evaluacion1

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.lifecycle.lifecycleScope
import com.example.evaluacion1.db.Planificador
import com.example.evaluacion1.db.PlanificadorDatabase
import com.example.evaluacion1.ui.theme.Evaluacion1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class RegistrarPlanificador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val latitud = intent.getStringExtra("latitud")!!
            val longitud = intent.getStringExtra("longitud")!!

            RegPlanUI{orden,
                nombreLocacion,
                rutaImagen,
                latitud,
                longitud,
                costoAlojamiento,
                costoTraslado,
                comentario -> RegPlan(orden,
                nombreLocacion,
                rutaImagen,
                latitud,
                longitud,
                costoAlojamiento,
                costoTraslado,
                comentario)}
        }
    }


    fun RegPlan(orden: Int,
                nombreLocacion: String,
                rutaImagen: String,
                latitud: String,
                longitud: String,
                costoAlojamiento: Int,
                costoTraslado: Int,
                comentario: String) {

        val contexto = this
        /*Se lanza la corrutina la que realiza el insert en la BD.
        * Se utiliza la clase Random para obtener una ID de producto aleatoria dentro de un rango.*/
        lifecycleScope.launch(Dispatchers.IO) {
            val productoDao = PlanificadorDatabase.getInstance(this@RegistrarPlanificador).planificadorDao()
            val nuevoProducto = Planificador(UUID.randomUUID(),
                orden,
                nombreLocacion,
                "RutaImagenDummy",
                latitud,
                longitud,
                costoAlojamiento,
                costoTraslado,
                comentario
            )
            productoDao.insertar(nuevoProducto)
            // Aca cambiamos al hilo principal para actualizar la UI.
            withContext(Dispatchers.Main) {
                /*Se llama a la funcion que genera el ALERT, se le pasan los parametros correspondientes*/
                dialogoInformacionRegistrar(contexto, resources.getString(R.string.msg_reg_prod), resources.getString(R.string.msg_alerta_titulo) )
            }
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegPlanUI(RegPlan: (orden: Int,
                        nombreLocacion: String,
                        rutaImagen: String,
                        _latitud: String,
                        _longitud: String,
                        costoAlojamiento: Int,
                        costoTraslado: Int,
                        comentario: String
              ) -> Unit){

    val contexto = LocalContext.current

    val (nombreLocacion, setNombreLocacion) = remember { mutableStateOf("") }
    val (orden, setOrden) = remember { mutableStateOf("") }
    val (costoTraslado, setCostoTraslado) = remember { mutableStateOf("") }
    val (costoAlojamiento, setCostoAlojamiento) = remember { mutableStateOf("") }
    val (latitud, setLatitud) = remember { mutableStateOf("") }
    val (longitud, setLongitud) = remember { mutableStateOf("") }
    val (uriImagen, setUriImagen) = remember { mutableStateOf("") }
    var comentario by remember { mutableStateOf("") }

    Column(

        modifier = Modifier.verticalScroll(rememberScrollState()),
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
                fontSize = 20.sp,
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
            value = latitud,
            onValueChange = setLatitud,
            label = { Text(text = "Latitud") }
        )
        TextField(
            value = longitud,
            onValueChange = setLongitud,
            label = { Text(text = "Longitud") }
        )
        Spacer(modifier =  Modifier.height(5.dp))
        Button(onClick={
            val intent = Intent(contexto, ObtenerUbicacion::class.java)
            intent.putExtra("nombre", nombreLocacion)
            intent.putExtra("latitud", longitud)
            intent.putExtra("longitud", latitud)
            contexto.startActivity(intent)
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
            label = { Text("Comentario | Detalle") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Default),
            maxLines = 20
        )
        Button(onClick={
            RegPlan(orden.toInt(), nombreLocacion , uriImagen , latitud,longitud ,
                    costoTraslado.toInt(), costoAlojamiento.toInt(), comentario)
        }){
            Text(text ="Ver lista de lugares a visitar")
        }

    }


}


fun dialogoInformacionRegistrar(contexto: Context, msgInfo: String, titulo: String ) {
    val builder = AlertDialog.Builder(contexto)
    builder.setTitle(titulo)
    builder.setMessage(msgInfo)
    builder.setPositiveButton("OK") { dialog, which ->
       // contexto.startActivity(Intent(contexto, MainActivity::class.java)) /* REDIRECCIONAMOS a la vista de LISTA PRODUCTOS*/
    }
    builder.setCancelable(false)

    builder.show()
}