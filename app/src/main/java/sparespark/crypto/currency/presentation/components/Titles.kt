package sparespark.crypto.currency.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun SubTitle(
    modifier: Modifier = Modifier,
    subTitle: String,
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 12.sp else 22.sp)
    }
    Text(
        modifier = modifier,
        text = subTitle,
        color = Color.DarkGray,
        fontSize = titleSize,
        fontWeight = FontWeight.Light,
    )
}

@Composable
fun MainTitle(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = Color.Black,
    textAlign: TextAlign = TextAlign.Start,
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 16.sp else 26.sp)
    }
    Text(
        modifier = modifier,
        text = title,
        color = color,
        fontSize = titleSize,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
    )
}

