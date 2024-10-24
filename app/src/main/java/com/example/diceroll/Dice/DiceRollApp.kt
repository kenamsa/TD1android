import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diceroll.Dice.DiceViewModel
import com.example.diceroll.R

@Composable
fun DiceRollApp() {
    val viewModel: DiceViewModel = viewModel()
    val player1Points by viewModel.player1Points.collectAsState(initial = 0)
    val player2Points by viewModel.player2Points.collectAsState(initial = 0)
    val currentRoll by viewModel.currentRoll.collectAsState(initial = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display Dice Image based on the currentRoll value
        if (currentRoll > 0) {
            val diceImageResource = when (currentRoll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.dice_1 // Default image
            }
            Image(
                painter = painterResource(id = diceImageResource),
                contentDescription = "Dice with roll $currentRoll",
                modifier = Modifier.width(100.dp).height(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Player 1 Points: $player1Points")
        Text(text = "Player 2 Points: $player2Points")

        Row {
            Button(onClick = { viewModel.rollDice(1) }) {
                Text("Roll for Player 1")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { viewModel.rollDice(2) }) {
                Text("Roll for Player 2")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.resetGame() }) {
            Text("Reset Game")
        }
    }
}
