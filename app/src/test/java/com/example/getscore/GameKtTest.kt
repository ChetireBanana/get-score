package com.example.getscore

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameKtTest {

    @Test
    fun `gameScore should return right score of a game whit offset`(){
        val testGame = arrayOf(
            Stamp(0, Score(0,0)),
            Stamp(10, Score(0,1)),
            Stamp(30, Score(1,1)),
            Stamp(50, Score(2,1)),
        )

        assertEquals(Score(0,0), getScore(testGame,0))
        assertEquals(Score(0,1), getScore(testGame,20))
        assertEquals(Score(1,1), getScore(testGame,30))
        assertEquals(Score(2,1), getScore(testGame,100))

    }

    @Test
    fun `gameScore should return Score(0,0) before the first stamp or negative value`(){
        val testGame = arrayOf(
            Stamp(10, Score(0,1)),
            Stamp(30, Score(1,1)),
            Stamp(50, Score(2,1)),
        )

        assertEquals(Score(0,0), getScore(testGame, 5))
        assertEquals(Score(0,0), getScore(testGame, -5))
    }

}