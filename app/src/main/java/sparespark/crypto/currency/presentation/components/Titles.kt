package sparespark.crypto.currency.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sparespark.crypto.currency.R
import sparespark.crypto.currency.core.secret.removeTags
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType


val logoFont = FontFamily(Font(R.font.font1))

@Composable
fun LogoTitle(
    title: String,
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 30.sp else 40.sp)
    }
    Text(
        text = title,
        fontSize = titleSize,
        textAlign = TextAlign.Start,
        color = Color.Red,
        fontFamily = logoFont,
    )
}

@Composable
fun MainTitle(
    title: String,
    windowSize: WindowSize
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 14.sp else 24.sp)
    }
    Text(
        text = title,
        color = Color.Black,
        fontSize = titleSize,
        fontWeight = FontWeight.Black,
        fontFamily = FontFamily.Serif
    )
}

@Composable
fun SubTitle(
    modifier: Modifier = Modifier,
    title: String,
    windowSize: WindowSize,
    color: Color = Color.DarkGray,
) {
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 12.sp else 22.sp)
    }
    Text(
        modifier = modifier,
        text = removeTags(title) ?: "",
        color = color,
        fontSize = titleSize,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Serif
    )
}


@Composable
fun ErrorTitle(
    title: String,
    windowSize: WindowSize
) {
    /*

    :(
    Error Message

    * */
    val titleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 60.sp else 70.sp)
    }
    val subTitleSize by remember(key1 = windowSize) {
        mutableStateOf(if (windowSize.width == WindowType.Compact) 14.sp else 24.sp)
    }
    val showIcon by remember(key1 = windowSize) {
        mutableStateOf(windowSize.width == WindowType.Expanded)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = titleSize)) {
                    append(":(")
                }
                append("\n\n${title}")
            },
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .align(Alignment.CenterStart),
            fontSize = subTitleSize
        )
    }
}

@Composable
fun ColoredTitle(
    firstRedTitle: String,
    firstBlackTitle: String,
    SecRedTitle: String = "",
    secBlackTitle: String = "",
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
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = titleSize,
                    fontFamily = logoFont
                )
            ) {
                append(firstRedTitle)
            }
            append(firstBlackTitle)
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontSize = subTitleSize
                )
            ) {
                append(SecRedTitle)
            }
            append(secBlackTitle)
        },
        color = Color.Black,
        fontSize = subTitleSize,
        fontWeight = FontWeight.ExtraBold,
        textAlign = TextAlign.Start,
        fontFamily = logoFont
    )
}