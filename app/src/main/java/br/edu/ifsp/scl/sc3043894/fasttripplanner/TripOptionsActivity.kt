package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    Column(
        modifier = modifier.fillMaxWidth().padding(5.dp)) {
        Text(
            text = "${destiny}, ${duration}, $dailyBudget"
        )
    }


}

@Preview(showBackground = true)
@Composable
fun TripOptionsPreview(){
    FastTripPlannerTheme() {
        TripOptions(Modifier,"","","")
    }
}



