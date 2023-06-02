package io.parrotsoftware.qatest.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.parrotsoftware.qatest.presentation.theme.Orange
import io.parrotsoftware.qatest.presentation.theme.TextStyle_Info
import io.parrotsoftware.qatest.presentation.theme.TextStyle_MainTitle
import io.parrotsoftware.qatest.presentation.theme.TextStyle_SecondTitle
import io.parrotsoftware.qatest.presentation.theme.bold

@Composable
fun ScreenDetail() {
    Column {
        LoadImage(
            "https://d1ralsognjng37.cloudfront.net/b49451f6-4f81-404e-bb97-2e486100b2b8.jpeg",
            Modifier
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            elevation = 4.dp,
            backgroundColor = Orange,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                ProductName()
                ProductDescription()
                ProductAvailability()
            }
        }
    }
}

@Composable
fun ProductName() {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Combo Amigos",
            style = TextStyle_MainTitle,
            color = Color.White
        )
        Text(
            text = "$13.00",
            style = TextStyle_SecondTitle.bold(),
            color = Color.White
        )
    }
}

@Composable
fun ProductDescription() {
    Text(
        modifier = Modifier
            .padding(top = 20.dp),
        text = "Descripción:",
        style = TextStyle_SecondTitle.bold(),
        color = Color.White
    )
    Text(
        modifier = Modifier
            .padding(top = 5.dp),
        text = "2 Subs de 15 cm (elige entre Jamón de Pavo, Sub de Pollo o Atún) + 2 bebidas embotelladas de 600 ml + 2 Bolsas de papas Sabritas o 2 galletas.",
        style = TextStyle_Info,
        color = Color.White
    )
}

@Composable
fun ProductAvailability() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Text(
            text = "Disponible",
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}
