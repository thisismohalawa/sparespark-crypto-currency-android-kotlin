package sparespark.crypto.currency.presentation.coinslist.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle(subTitle: String) {
    Text(
        text = subTitle,
        color = Color.Gray,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun MainTitle(mainTitle: String) {
    Text(
        text = mainTitle,
        color = Color.Black,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start
    )
}
