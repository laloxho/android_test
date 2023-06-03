package io.parrotsoftware.qatest.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val TextStyle_MainTitle = TextStyle(
    fontSize = 24.sp,
    fontWeight = FontWeight.Bold
)

val TextStyle_SecondTitle = TextStyle(
    fontSize = 18.sp,
    fontWeight = FontWeight.Bold
)

val TextStyle_Info = TextStyle(
    fontSize = 16.sp,
)

fun TextStyle.bold() = copy(fontWeight = FontWeight.Bold)
