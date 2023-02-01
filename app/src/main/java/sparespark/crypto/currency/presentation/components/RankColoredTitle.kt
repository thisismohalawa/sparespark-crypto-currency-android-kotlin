package sparespark.crypto.currency.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun MainTitleHelper(
    title: String,
    subTitle: String,
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 32.sp else 42.sp)
    }
    val subTitleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 16.sp else 26.sp)
    }

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = titleSize,
                    fontWeight = FontWeight.ExtraBold,
                )
            ) {
                append(title)
            }
            append(subTitle)
        },
        color = Color.DarkGray,
        fontSize = subTitleSize,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Start
    )
}

@Composable
fun RankColoredTitle(
    redText: String,
    blackText: String,
    redSubTitle: String = "",
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 40.sp else 55.sp)
    }
    val subTitleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 30.sp else 45.sp)
    }

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(color = Color.Red, fontSize = titleSize)
            ) {
                append(redText)
            }
            append(blackText)
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = subTitleSize
                )
            ) {
                append("$redSubTitle ")
            }
            // append("coin")
        },
        color = Color.Black,
        fontSize = subTitleSize,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Start
    )
}
