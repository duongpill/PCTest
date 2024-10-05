package com.duongnh.pctest.presentation.home_screen

import com.duongnh.pctest.domain.model.User

data class HomeUIState(
    val users: List<User> = emptyList(),
    val user: User = User(),
    val nextUser: User = User(),
    val isLoading: Boolean = false
)