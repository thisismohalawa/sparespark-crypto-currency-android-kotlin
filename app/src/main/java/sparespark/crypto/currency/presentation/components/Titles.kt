package sparespark.crypto.currency.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun SubTitle(subTitle: String, windowSize: WindowSize) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 14.sp else 24.sp)
    }
    Text(
        text = subTitle,
        color = Color.Gray,
        fontSize = titleSize
    )
}

@Composable
fun MainTitle(mainTitle: String, windowSize: WindowSize) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 16.sp else 26.sp)
    }
    Text(
        text = mainTitle,
        color = Color.Black,
        fontSize = titleSize,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
}
