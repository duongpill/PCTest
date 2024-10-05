package com.duongnh.pctest.presentation.home_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.duongnh.pctest.R
import com.duongnh.pctest.domain.model.User
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SwipeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    user: User,
    swipeLeft: () -> Unit,
    swipeRight: () -> Unit,
    onSwipe: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        SwipeCard(user = user, swipeLeft = swipeLeft, swipeRight = swipeRight, onSwipe = onSwipe)
        // Actions buttons
        ActionsButtons(swipeLeft, swipeRight)
    }
}

@Composable
fun SwipeCard(
    user: User,
    swipeLeft: () -> Unit,
    swipeRight: () -> Unit,
    onSwipe: (Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember { Animatable(0f) }

    Card(
        modifier = Modifier
            .offset {
                IntOffset(offsetX.value.roundToInt(), 0)
            }
            .padding(15.dp)
            .draggable(
                state = rememberDraggableState { delta ->
                    coroutineScope.launch {
                        offsetX.snapTo(offsetX.value + delta)
                    }
                },
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    onSwipe(true)
                },
                onDragStopped = {
                    coroutineScope.launch {
                        offsetX.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(
                                durationMillis = 300,
                                delayMillis = 0,
                                easing = FastOutSlowInEasing
                            )
                        )
                    }
                    if (offsetX.value > 300f) {
                        // Swiped right
                        swipeRight()
                        onSwipe(false)
                    } else if (offsetX.value < -300f) {
                        // Swiped left
                        swipeLeft()
                        onSwipe(false)
                    } else {
                        // No swipe
                        onSwipe(false)
                    }
                }
            )
    ) {
        UserCard(user = user)
    }
}

@Composable
fun ActionsButtons(swipeLeft: () -> Unit, swipeRight: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Card(
            onClick = { swipeRight() },
            shape = RoundedCornerShape(80.dp),
            modifier = Modifier.padding(end = 5.dp),
        ) {
            Icon(
                modifier = Modifier
                    .size(80.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.ic_disfavour),
                contentDescription = null
            )
        }
        Card(
            onClick = { swipeLeft() },
            shape = RoundedCornerShape(80.dp),
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(80.dp)
                    .padding(15.dp),
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = null
            )
        }
    }
}