package sparespark.crypto.currency.presentation.components

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun BottomFloatingButton(
    title: String,
    windowSize: WindowSize,
    imageVector: ImageVector = Icons.Filled.Info,
    onItemClicked: () -> Unit,
) {
    ExtendedFloatingActionButton(
        icon = { Icon(imageVector = imageVector, contentDescription = null, tint = Color.White) },
        text = { SubTitle(title = title, color = Color.White, windowSize = windowSize) },
        backgroundColor = Color.Red,
        onClick = { onItemClicked() },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    )
}
