package com.example.diceroll.Dice


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class DiceViewModel : ViewModel() {
    private val _player1Points = MutableStateFlow(0)
    private val _player2Points = MutableStateFlow(0)
    private val _currentRoll = MutableStateFlow(0)

    val player1Points: StateFlow<Int> get() = _player1Points
    val player2Points: StateFlow<Int> get() = _player2Points
    val currentRoll: StateFlow<Int> get() = _currentRoll

    fun rollDice(player: Int) {
        val roll = Random.nextInt(1, 7)
        _currentRoll.value = roll
        if (player == 1) {
            _player1Points.value += roll
        } else {
            _player2Points.value += roll
        }
    }

    fun resetGame() {
        _player1Points.value = 0
        _player2Points.value = 0
        _currentRoll.value = 0
    }
}

