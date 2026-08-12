package com.example.battlesweeper.util

import androidx.compose.ui.graphics.Color

object UIConstants {

    const val TILE_SIZE = 30
    const val GRID_PADDING = 20

    //seven segment display
    const val SSD_SEGMENT_HORIZONTAL_WIDTH_SCALE = 0.8   // percent of the width of an ssd digit that a horizontal segment should take up
    const val SSD_SEGMENT_HORIZONTAL_HEIGHT_SCALE = 0.1  // percent of the height of an ssd digit that a horizontal segment should take up
    const val SSD_SEGMENT_VERTICAL_WIDTH_SCALE = 0.2     // percent of the width of an ssd digit that a vertical segment should take up
    const val SSD_SEGMENT_VERTICAL_HEIGHT_SCALE = 0.4    // percent of the height of an ssd digit that a vertical segment should take up

    const val SSD_COUNTER_PADDING = 4                   //padding around a set of number in an SSD counter.
    const val SSD_COUNTER_DIGIT_SEPARATION = 10          //DP between each digit when displaying multiple digits in a seven segment display
    const val SSD_DIGIT_HEIGHT_TO_WIDTH_RATIO = 0.4     //each digit should have a width this much smaller than their height

    val SSD_SEGMENT_ACTIVE_COLOR = Color.Red
    val SSD_SEGMENT_INACTIVE_COLOR = Color.DarkGray

    //BS_BORDER
    val BORDERCOLOR_LIGHTGRAY = Color.LightGray
    val BORDERCOLOR_GRAY = Color.Gray
    val BORDERCOLOR_WHITE = Color.White


}