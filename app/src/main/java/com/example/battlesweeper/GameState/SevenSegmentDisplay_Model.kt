package com.example.battlesweeper.GameState

data class DigitSegments(
    val topLeft: Boolean,
    val topMid: Boolean,
    val topRight: Boolean,
    val midMid: Boolean,
    val botLeft: Boolean,
    val botMid: Boolean,
    val botRight: Boolean
)

val digitMap = mapOf(
    0 to DigitSegments(true, true, true, false, true, true, true),
    1 to DigitSegments(false, false, true, false, false, false, true),
    2 to DigitSegments(false, true, true, true, true, true, false),
    3 to DigitSegments(false, true, true, true, false, true, true),
    4 to DigitSegments(true, false, true, true, false, false, true),
    5 to DigitSegments(true, true, false, true, false, true, true),
    6 to DigitSegments(true, true, false, true, true, true, true),
    7 to DigitSegments(false, true, true, false, false, false, true),
    8 to DigitSegments(true, true, true, true, true, true, true),
    9 to DigitSegments(true, true, true, true, false, true, true),
    "E" to DigitSegments(true, true, false, true, true, true, false)
    )