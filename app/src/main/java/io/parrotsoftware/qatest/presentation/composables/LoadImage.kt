package io.parrotsoftware.qatest.presentation.composables

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import io.parrotsoftware.qatest.R

@Composable
fun LoadImage(url: String?, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit,
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> LoadingCircular()
            is AsyncImagePainter.State.Error -> ImageError()
            else -> SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun ImageError() {
    Icon(
        painter = painterResource(id = R.drawable.ic_place_holder),
        contentDescription = null,
    )
}
