package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3043894.fasttripplanner.ui.theme.FastTripPlannerTheme

class TripOptionsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val destiny = intent.getStringExtra("EXTRA_DESTINY") ?:""
        val duration = intent.getStringExtra("EXTRA_DURATION") ?:""
        val  dailyBudget = intent.getStringExtra("EXTRA_DAILY_BUDGET") ?:""
        setContent {
            FastTripPlannerTheme {
                Scaffold(
                    topBar = {FastTripPlannerTopBar(onBack = {finish()})},
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TripOptions(
                        modifier = Modifier.padding(innerPadding),
                        destiny = destiny,
                        duration = duration,
                        dailyBudget = dailyBudget,
                    )
                }
            }
        }

    }
}

@Composable
fun TripOptions(modifier: Modifier = Modifier, destiny: String,duration:String, dailyBudget: String){

    val accommodationOptions = listOf(
        "Econômica",
        "Conforto",
        "Luxo"
    )
    val servicesOptions = listOf(
        "Transporte",
        "Alimentação",
        "Passeios"
    )

    var accommodationSelected by rememberSaveable() { mutableStateOf("Econômica") }
    val servicesSelected  =  rememberSaveable() { mutableStateListOf<String>() }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
       ) {
        Text(
                text = "Escolha a Hospedagem",
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 24.sp,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            accommodationOptions.forEach { tripOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = accommodationSelected == tripOption,
                        onClick = { accommodationSelected = tripOption }
                    )
                    Text(
                        text = tripOption
                    )
                }
            }
        }

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Econômica → 1.0\n" +
                    "Conforto → 1.5\n" +
                    "Luxo → 2.2"
        )


        Text(
            text = "Escolha os Serviços",
            modifier= Modifier
                .fillMaxWidth()
                .padding(10.dp),
            fontSize = 24.sp,
        )

            servicesOptions.forEach { servicesOption->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = servicesSelected.contains(servicesOption),
                        onCheckedChange = { isChecked ->
                            if(isChecked){
                                servicesSelected.add(servicesOption)
                            }else{
                                servicesSelected.remove(servicesOption)
                            }
                        }
                    )
                    Text(
                        text = servicesOption
                    )
                }

        }
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Transporte → + R\$ 300\n" +
                    "Alimentação → + R\$ 50/dia\n" +
                    "Passeios → + R\$ 120/dia"
        )

            Button(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                onClick = {}
            ) {
                Text(text ="Calcular")
            }


    }

}

@Preview(showBackground = true)
@Composable
fun TripOptionsPreview(){
    FastTripPlannerTheme() {
        TripOptions(Modifier,"","","")
    }
}



