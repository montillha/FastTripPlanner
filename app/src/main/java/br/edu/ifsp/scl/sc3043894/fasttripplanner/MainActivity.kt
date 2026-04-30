package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifsp.scl.sc3043894.fasttripplanner.ui.theme.FastTripPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastTripPlannerTheme {
                Scaffold(
                    topBar = {FastTripPlannerTopBar()},
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FastTripPlanner(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FastTripPlannerTopBar(onBack: (()-> Unit)?=null){
    TopAppBar(
        title = { Text(text = stringResource(R.string.fast_trip_planner))},
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            if(onBack != null){
                IconButton( onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar"
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun FastTripPlannerTopBarPreview(){
    FastTripPlannerTopBar()
}


@Composable
fun FastTripPlanner( modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var destiny by rememberSaveable() { mutableStateOf("") }
    var duration by rememberSaveable() { mutableStateOf("") }
    var dailyBudget by rememberSaveable() { mutableStateOf("") }

    fun validateData(): Boolean{
        val durationInt = duration.toIntOrNull()
        val dailyBudgetDouble = dailyBudget.toDoubleOrNull()

        if(destiny.isBlank()){
            Toast.makeText(context, "Informe o destino da viagem", Toast.LENGTH_SHORT).show()
            return false
        }

        if(durationInt == null || durationInt <= 0) {
            Toast.makeText(context, "Informe um número de dias válido e maior que zero", Toast.LENGTH_SHORT).show()
            return false
        }
        if(dailyBudgetDouble== null || dailyBudgetDouble <= 0) {
            Toast.makeText(context, "Informe um orçamento diário válido e maior que zero", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = destiny,
            onValueChange = {destiny = it},
            label =  { Text( text = "Destino") }
        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = duration,
            onValueChange = {duration = it},
            label =  { Text( text = "Número de dias") }

        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = dailyBudget,
            onValueChange = {dailyBudget = it},
            label =  { Text( text = "Orçamento Diário") }
        )

        Button(
            onClick = {
                if(validateData()){
                    val intent  = Intent(context, TripOptionsActivity::class.java)
                    intent.putExtra("EXTRA_DESTINY",destiny)
                    intent.putExtra("EXTRA_DURATION",duration.toInt())
                    intent.putExtra("EXTRA_DAILY_BUDGET",dailyBudget.toDouble())
                    context.startActivity(intent)
                }

            },
            modifier = Modifier.padding(10.dp)
        ) { Text(text = "Escolher opções de viagem")}
    }



}

@Preview(showBackground = true)
@Composable
fun FastTripPlannerPreview() {
    FastTripPlannerTheme {
        FastTripPlanner()
    }
}