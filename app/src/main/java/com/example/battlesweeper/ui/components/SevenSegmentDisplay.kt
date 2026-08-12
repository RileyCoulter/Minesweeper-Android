package com.example.battlesweeper.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.battlesweeper.GameState.digitMap
import com.example.battlesweeper.util.UIConstants
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.roundToInt


@Composable
private fun DigitSegment(
    active: Boolean,
    modifier: Modifier = Modifier,
    activeColor: Color = UIConstants.SSD_SEGMENT_ACTIVE_COLOR,
    inactiveColor: Color = UIConstants.SSD_SEGMENT_INACTIVE_COLOR
) {
    Box(
        modifier = modifier.background(
            if (active) activeColor else inactiveColor
        )
    )
}

@Composable
private fun SevenSegmentDigit(
    number: Int,
    digitHeight: Int,
    digitWidth: Int

) {
    val segments = digitMap[number] ?: digitMap["E"]!!

    Box(
        modifier = Modifier.size(digitWidth.dp, digitHeight.dp)
    ) {
        DigitSegment(
            segments.topMid,
            Modifier
                .align(Alignment.TopCenter)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_HORIZONTAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_HORIZONTAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.midMid,
            Modifier
                .align(Alignment.Center)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_HORIZONTAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_HORIZONTAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.botMid,
            Modifier
                .align(Alignment.BottomCenter)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_HORIZONTAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_HORIZONTAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.topLeft,
            Modifier
                .align(Alignment.TopStart)
                .padding(top = (digitHeight * (1 - (UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE * 2)) / 4).dp)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_VERTICAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.topRight,
            Modifier
                .align(Alignment.TopEnd)
                .padding(top = (digitHeight * (1 - (UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE * 2)) / 4).dp)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_VERTICAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.botLeft,
            Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = (digitHeight * (1 - (UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE * 2)) / 4).dp)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_VERTICAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE).dp
                )
        )

        DigitSegment(
            segments.botRight,
            Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = (digitHeight * (1 - (UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE * 2)) / 4).dp)
                .size(
                    width = (digitWidth * UIConstants.SSD_SEGMENT_VERTICAL_WIDTH_SCALE).dp,
                    height = (digitHeight * UIConstants.SSD_SEGMENT_VERTICAL_HEIGHT_SCALE).dp
                )
        )
    }
}

@Composable
fun SevenSegmentColon(
    digitHeight: Int,
    digitWidth: Int,
    activeColor: Color = UIConstants.SSD_SEGMENT_ACTIVE_COLOR,
    backgroundColor: Color = Color.Black
) {
    Box(
        modifier = Modifier
            .width(digitWidth.dp)
            .height(digitHeight.dp)
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            //Colon Dot 1
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.4f)
                        .aspectRatio(1f)
                        .rotate(45f)
                        .background(activeColor)
                )
            }

            //Colon Dot 2
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.4f)
                        .aspectRatio(1f)
                        .rotate(45f)
                        .background(activeColor)
                )
            }
        }
    }
}


@Composable
fun SevenSegmentCounter(
    number: Int,
    digits: Int,
    counterHeight: Int,
    counterWidth: Int,
    modifier: Modifier,
    backgroundColor: Color = Color.Black,
    boxInternalPadding: Int = UIConstants.SSD_COUNTER_PADDING
) {

    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(counterWidth.dp)
            .height(counterHeight.dp),
        contentAlignment = Alignment.Center
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val maxHeightFromCounterHeight = counterHeight * 1.0 - (2 * boxInternalPadding)
            val maxWidthFromCounterHeight = maxHeightFromCounterHeight * UIConstants.SSD_DIGIT_HEIGHT_TO_WIDTH_RATIO

            val maxWidthFromCounterWidth = (counterWidth * 1.0 - (2 * boxInternalPadding) - ((digits - 1) * UIConstants.SSD_COUNTER_DIGIT_SEPARATION)) / digits
            val maxHeightFromCounterWidth = maxWidthFromCounterWidth * (1 / UIConstants.SSD_DIGIT_HEIGHT_TO_WIDTH_RATIO)

            SevenSegmentDigit(
                number = floor((number / 10.toDouble().pow(digits - 1))).toInt() % 10,
                digitHeight = floor(minOf(maxHeightFromCounterWidth, maxHeightFromCounterHeight)).toInt(),
                digitWidth = floor(minOf(maxWidthFromCounterHeight, maxWidthFromCounterWidth)).toInt()
                )
            repeat(digits - 1)
            {
                col ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(UIConstants.SSD_COUNTER_DIGIT_SEPARATION.dp))

                    SevenSegmentDigit(
                        number = floor((number / 10.toDouble().pow(digits - 2 - col))).toInt() % 10,
                        digitHeight = floor(minOf(maxHeightFromCounterWidth, maxHeightFromCounterHeight)).toInt(),
                        digitWidth = floor(minOf(maxWidthFromCounterHeight, maxWidthFromCounterWidth)).toInt()
                    )
                }

            }
        }

    }

}



@Composable
fun SevenSegmentTimer(
    totalSeconds: Int,
    minuteDigits: Int,
    timerHeight: Int,
    timerWidth: Int,
    modifier: Modifier,
    backgroundColor: Color = Color.Black,
    boxInternalPadding: Int = UIConstants.SSD_COUNTER_PADDING
) {
    var minutes: Int = (totalSeconds / 60).coerceAtMost(99)
    var remainderSeconds: Int = totalSeconds % 60
   if(totalSeconds > 6000) {
       remainderSeconds = 59
   }

    Box(
        modifier = modifier
            .background(backgroundColor)
            .width(timerWidth.dp)
            .height(timerHeight.dp)
    )
    {
        Row() {
            val digitWidth: Int = ((timerWidth / (minuteDigits + 2 + 0.5))).roundToInt()
            SevenSegmentCounter(minutes, minuteDigits, timerHeight, digitWidth * minuteDigits, Modifier)
            SevenSegmentColon(timerHeight, (digitWidth * 0.5).roundToInt())
            SevenSegmentCounter(remainderSeconds, 2, timerHeight, digitWidth * 2, Modifier)
        }

    }
}
















