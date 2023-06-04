package io.parrotsoftware.qatest.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.parrotsoftware.qatest.R
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.models.ResponseState
import io.parrotsoftware.qatest.presentation.composables.LoadImage
import io.parrotsoftware.qatest.presentation.composables.LoadingCircular
import io.parrotsoftware.qatest.presentation.theme.Green
import io.parrotsoftware.qatest.presentation.theme.Orange
import io.parrotsoftware.qatest.presentation.theme.TextStyle_Info
import io.parrotsoftware.qatest.presentation.theme.TextStyle_MainTitle
import io.parrotsoftware.qatest.presentation.theme.TextStyle_SecondTitle
import io.parrotsoftware.qatest.presentation.theme.bold

@Composable
fun ScreenDetail(showDetailViewModel: ShowDetailViewModel = hiltViewModel(), productId: String) {
    LaunchedEffect(key1 = Unit) {
        showDetailViewModel.getProductById(productId)
    }

    when (val product = showDetailViewModel.product.value) {
        is ResponseState.Loading -> LoadingCircular()
        is ResponseState.Success -> ShowData(product.responseTo())
    }
}

@Composable
fun ShowData(product: Product) {
    BoxWithConstraints {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(this@BoxWithConstraints.maxHeight),
            ) {
                LoadImage(
                    product.image,
                    Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    elevation = 8.dp,
                    backgroundColor = Orange,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        ProductName(product.name, product.price.toString())
                        ProductDescription(product.description)
                        ProductAvailability(product.isAvailable)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductName(name: String, price: String) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = TextStyle_MainTitle,
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Text(
            text = "$$price",
            style = TextStyle_SecondTitle.bold(),
            color = Color.White,
            maxLines = 1,
        )
    }
}

@Composable
fun ProductDescription(description: String) {
    Text(
        modifier = Modifier
            .padding(top = 20.dp),
        text = stringResource(id = R.string.detail_description),
        style = TextStyle_SecondTitle.bold(),
        color = Color.White,
    )
    Text(
        modifier = Modifier
            .padding(top = 15.dp),
        text = description,
        style = TextStyle_Info,
        color = Color.White,
        lineHeight = 28.sp,
    )
}

@Composable
fun ProductAvailability(available: Boolean) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if (available) Green else Color.LightGray),
        modifier = Modifier
            .padding(25.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
    ) {
        Text(
            text = stringResource(id = if (available) R.string.detail_available else R.string.detail_unavailable),
            color = if (available) Color.White else Color.Black,
            modifier = Modifier.padding(8.dp),
        )
    }
}
