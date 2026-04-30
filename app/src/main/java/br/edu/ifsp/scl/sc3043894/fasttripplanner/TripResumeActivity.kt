package br.edu.ifsp.scl.sc3043894.fasttripplanner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3043894.fasttripplanner.ui.theme.FastTripPlannerTheme

class TripResumeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val destiny = intent.getStringExtra("EXTRA_DESTINY") ?:""
        val duration = intent.getIntExtra("EXTRA_DURATION",1)
        val dailyBudget = intent.getDoubleExtra("EXTRA_DAILY_BUDGET",1.0)
        val accommodation = intent.getStringExtra("EXTRA_ACCOMMODATION") ?: ""
        val services = intent.getStringArrayListExtra("EXTRA_SERVICES")?: arrayListOf()

        setContent {
            FastTripPlannerTheme {
                Scaffold(
                    topBar = {FastTripPlannerTopBar(onBack = {finish()})},
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TripResume(
                        modifier = Modifier.padding(innerPadding),
                        destiny = destiny,
                        duration = duration,
                        dailyBudget = dailyBudget,
                        accommodation = accommodation,
                        services = services,
                    )
                }
            }
        }

    }
}

@Composable
fun TripResume(
    modifier: Modifier = Modifier,
               destiny: String,
               duration: Int,
               dailyBudget: Double,
               accommodation: String,
               services : List<String>
    ){

    val context = LocalContext.current

    val accommodationOptions = mapOf(
        "Econômica" to 1.0,
        "Conforto" to 1.5,
        "Luxo" to 2.2
    )
    val servicesOptions = mapOf(
        "Transporte" to 300.0,
        "Alimentação" to 50.0,
        "Passeios" to 120.0
    )

    fun calculateTotal() : Double {
        val totalWithAccommodation = (dailyBudget * duration) *
                accommodationOptions.getValue(accommodation)

        val totalServices = services.sumOf { service ->
            val value = servicesOptions.getValue(service)
            if(service == "Transporte"){
                value
            }else{
                value*duration
            }
        }
        return totalWithAccommodation + totalServices
    }

    Column(
        modifier = modifier.run {
            fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(10.dp)
        },

        ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text =  "Informações da Viagem",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center

        )

        Text(
            modifier = Modifier.padding(10.dp),
            text =  "Destino: ${destiny}"
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text =  "Número de Dias: ${duration}"
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text =  "Orçamento Diário: ${dailyBudget}"
        )

        Text(
            modifier = Modifier.padding(10.dp),
            text =  "Acomodação: ${accommodation} "
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text =  "Serviços: ${services.joinToString(", ")}"
        )
        Text( modifier = Modifier.padding(10.dp),
            text =  "Total:  ${"%.2f".format(calculateTotal())}"
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            onClick = {
                val intent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }

                //FLAG_ACTIVITY_CLEAR_TOP - remove tela 3 e 2 da pilha e volta para a tela 1
                //FLAG_ACTIVITY_NEW_TASK - informa em que tarefa inicia a tela 1

                context.startActivity(intent)
            }

        ) {
            Text(text = "Reiniciar")
        }


    }

}

@Preview(showBackground = true)
@Composable
fun TripResumePreview(){
    TripResume(
        Modifier,
        "Praia",
        1,
        1.0,
        "Luxo",
        listOf("Alimentação")
    )
}