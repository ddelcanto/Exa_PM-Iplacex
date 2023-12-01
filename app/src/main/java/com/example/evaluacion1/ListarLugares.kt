package com.example.evaluacion1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evaluacion1.db.Planificador
import com.example.evaluacion1.db.PlanificadorDatabase
import com.example.evaluacion1.ui.theme.Evaluacion1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListarLugares : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val planDAO = PlanificadorDatabase.getInstance(this).planificadorDao()

        setContent {
            ListaLugaresUI()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaLugaresUI(){

    val contexto = LocalContext.current
    val (lugares, setLugares) =  remember { mutableStateOf(emptyList<Planificador>())}

    //Lanzo la corrutina para llenar la tabla principal de la vista
    LaunchedEffect(lugares){
        withContext(Dispatchers.IO){
            val dao = PlanificadorDatabase.getInstance( contexto ).planificadorDao()
            setLugares(dao.getAll())
        }
    }
    Row(
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = stringResource(R.string.msg_ListaNombre))
        }
    }
    Row(){
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

        }
    }

    /* Boton para direccionar a la vista de registro de un nuevo producto
    * Se utiliza stringResource para llamar al recurso String correspondiente.
    * */
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick={

        }){

        }
    }
}


@Composable
fun LugarItemUI(plan:Planificador, guardar:() -> Unit){
    val contexto = LocalContext.current
    val alcanceCorr = rememberCoroutineScope()
    /* Se setean las variables con los datos del recurso STRINGS*/
    val msg_quitar = stringResource(R.string.msg_quitar)
    val msg_agregar = stringResource(R.string.msg_agregar)
    val msg_eliminar = stringResource(R.string.msg_eliminar)

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical= 20.dp , horizontal = 20.dp)
    ){
            Icon(
                Icons.Filled.Check,
                contentDescription = "Comprado",
                tint = Color.Green,
                modifier = Modifier.clickable {
                    alcanceCorr.launch(Dispatchers.IO){
                        PlanificadorDatabase.getInstance( contexto ).planificadorDao().actualizar(plan)
                        withContext(Dispatchers.Main) {

                        }
                        guardar()
                    }
                }
            )

        Spacer(modifier =  Modifier.width(20.dp))
        Text(
            text = plan.nombreLugar.toString(),
            modifier = Modifier.weight(2f)
        )
        Icon(
            Icons.Filled.Delete,
            contentDescription = "Eliminar",
            tint = Color.Red,
            modifier = Modifier.clickable {
                alcanceCorr.launch(Dispatchers.IO){

                    PlanificadorDatabase.getInstance( contexto ).planificadorDao().eliminar(plan)
                    withContext(Dispatchers.Main) {

                    }
                    guardar()
                }
            }
        )
    }
}

