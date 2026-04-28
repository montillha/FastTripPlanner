package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun FastTripPlannerTopBar(){
    TopAppBar(
        title = { Text(text = stringResource(R.string.fast_trip_planner))},
        modifier = Modifier.fillMaxWidth(),
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

    var destiny by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var dailyBudget by remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = destiny,
            onValueChange = {destiny = it},
            label =  { Text( text = "Destino") }
        )
        OutlinedTextField(
            value = duration,
            onValueChange = {duration = it},
            label =  { Text( text = "Número de dias") }

        )
        OutlinedTextField(
            value = dailyBudget,
            onValueChange = {dailyBudget = it},
            label =  { Text( text = "Orçamento Diário") }
        )

        Button(
            onClick = {},
            modifier = Modifier.padding(10.dp)
        ) { Text(text = "Choose Trip Options")}
    }

}

@Preview(showBackground = true)
@Composable
fun FastTripPlannerPreview() {
    FastTripPlannerTheme {
        FastTripPlanner()
    }
}