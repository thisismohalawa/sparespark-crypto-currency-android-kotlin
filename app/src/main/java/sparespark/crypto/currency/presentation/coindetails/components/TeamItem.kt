package sparespark.crypto.currency.presentation.coindetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sparespark.crypto.currency.data.remote.dto.coindetails.TeamMember
import sparespark.crypto.currency.presentation.components.SubTitle
import sparespark.crypto.currency.presentation.window.WindowSize

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    windowSize: WindowSize,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.Center
    ) {
        SubTitle(subTitle = teamMember.name, windowSize = windowSize)
        Spacer(modifier = Modifier.height(2.dp))
        SubTitle(subTitle = teamMember.position, windowSize = windowSize)
    }
}