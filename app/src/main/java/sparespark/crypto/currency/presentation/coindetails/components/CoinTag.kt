package sparespark.crypto.currency.presentation.coindetails.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize
import sparespark.crypto.currency.presentation.window.WindowType

@Composable
fun CoinTag(
    tag: String,
    windowSize: WindowSize
) {
    val roundedBorderSize by remember(key1 = windowSize) {
        mutableStateOf(
            if (windowSize.width == WindowType.Compact) 100.dp
            else 150.dp
        )
    }
    val roundedBorderPadding by remember(key1 = windowSize) {
        mutableStateOf(
            if (windowSize.width == WindowType.Compact) 10.dp
            else 20.dp
        )
    }
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.Red,
                shape = RoundedCornerShape(roundedBorderSize)
            )
            .padding(roundedBorderPadding)
    ) {
        SubTitle(subTitle = tag, windowSize = windowSize)
    }
}