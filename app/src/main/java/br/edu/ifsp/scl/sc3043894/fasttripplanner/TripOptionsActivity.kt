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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
                    topBar = {FastTripPlannerTopBar()},
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

    val tripOptions = listOf("Econômica","Conforto","Luxo");
    var optionSelected by rememberSaveable() { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxWidth().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
                text = "Escolha de Hospedagem",
                modifier= Modifier.fillMaxWidth().padding(10.dp),
                fontSize = 24.sp,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            // SpaceEvenly: distribui os filhos em espaço igual entre eles, nas bordas e entre cada item
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            tripOptions.forEach { tripOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = optionSelected == tripOption,
                        onClick = { optionSelected = tripOption }
                    )
                    Text(
                        text = tripOption
                    )
                }
            }
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



