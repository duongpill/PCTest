package com.duongnh.pctest.presentation.home_screen

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isVisible = remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    if (isVisible.value) {
        SwipeScreen(
            modifier = modifier,
            scrollState = scrollState,
            user = uiState.nextUser,
            swipeLeft = { viewModel.swipeLeft() },
            swipeRight = { viewModel.swipeRight() }) { isDragging ->
            isVisible.value = isDragging
        }
    }

    SwipeScreen(
        modifier = modifier,
        scrollState = scrollState,
        user = uiState.user,
        swipeLeft = { viewModel.swipeLeft() },
        swipeRight = { viewModel.swipeRight() }) { isDragging ->
        isVisible.value = isDragging
    }
}