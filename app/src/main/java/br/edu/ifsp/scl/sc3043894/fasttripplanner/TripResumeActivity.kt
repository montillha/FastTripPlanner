package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.sc3043894.fasttripplanner.ui.theme.FastTripPlannerTheme

class TripResumeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FastTripPlannerTheme {
                Scaffold(
                    topBar = {FastTripPlannerTopBar(onBack = {finish()})},
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TripResume(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }

    }
}

@Composable
fun TripResume(modifier: Modifier = Modifier){
    Column(
        modifier = modifier.fillMaxWidth().padding(5.dp),

        ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            text =  "Trip Resume",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center

        )

        Text(text =  "Destino: ")

        Text(text =  "Número de Dias: ")

        Text(text =  "Orçamento Diário:  ")

        Text(text =  "Acomodação:")

        Text(text =  "Serviços: ")

        Text(text =  "Total:")

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                onClick = {}

            ) {
                Text(text = "Reiniciar")
            }

        }


    }


}

@Preview(showBackground = true)
@Composable
fun TripResumePreview(){
    TripResume()
}