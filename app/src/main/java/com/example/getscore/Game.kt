package com.example.getscore

import kotlin.math.floor
import kotlin.random.Random


fun main() {
    val game = generateGame()
    println(getScore(game, 300000))
}

fun generateGame(): Array<Stamp> {
    val stamps = Array(TIMESTAMPS_COUNT) { _ -> Stamp(0, Score(0, 0)) }
    var currentStamp = stamps[0]
    for (i in 1 until TIMESTAMPS_COUNT) {
        currentStamp = generateStamp(currentStamp)
        stamps[i] = currentStamp
    }
    return stamps
}

fun generateStamp(previousValue: Stamp): Stamp {
    val scoreChanged = Random.nextFloat() > 1 - PROBABILITY_SCORE_CHANGED
    val homeScoreChange =
        if (scoreChanged && Random.nextFloat() > 1 - PROBABILITY_HOME_SCORE) 1 else 0
    val awayScoreChange = if (scoreChanged && homeScoreChange == 0) 1 else 0
    val offsetChange = (floor(Random.nextFloat() * OFFSET_MAX_STEP) + 1).toInt()

    return Stamp(
        previousValue.offset + offsetChange,
        Score(
            previousValue.score.home + homeScoreChange,
            previousValue.score.away + awayScoreChange
        )
    )
}

fun getScore(gameStamps: Array<Stamp>, offset: Int): Score {
    var left = 0
    var right = gameStamps.size - 1
    var resultIndex = -1

    while (left <= right) {
        val mid = (right + left) / 2
        if (gameStamps[mid].offset <= offset) {
            resultIndex = mid
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return if (resultIndex == -1) Score(0, 0) else gameStamps[resultIndex].score
}