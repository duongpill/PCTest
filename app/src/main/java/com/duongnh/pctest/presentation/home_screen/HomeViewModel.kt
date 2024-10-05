package com.duongnh.pctest.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duongnh.pctest.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState(isLoading = true))

    val uiState: StateFlow<HomeUIState> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        // Assuming you have a list of users
        val userList = listOf(
            User(
                1,
                "John Doe",
                18,
                "https://www.datingunchained.com/wp-content/uploads/2022/08/online-dating-profile-photographer-jpg-webp.webp",
                "I like to travel."
            ),
            User(
                2,
                "JinE",
                21,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTm2iOTcXHqKQFgbD1hFtmQ_viMMU0tQrDzhg&s",
                "I like to listen music."
            ),
            User(
                3,
                "Jack London",
                30,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQwU4G2JYAc_A99n4HqIzgXBujXEmL2lMdZA&s",
                "Want to predict the future."
            ),
            User(
                4,
                "Knox Ngo",
                26,
                "https://www.mtlelectronicmusic.com/artists/nineyard/profile_MG_0941%20-%20Tony%20Merguez_hu80ed016bbac75bf54e8d977eabc580b2_10294977_800x0_resize_q75_h2_box.webp",
                "Code and code anymore."
            ),
        )

        viewModelScope.launch {
            _uiState.update { state ->
                val nextUser = getNextUser(userList, userList.first())
                state.copy(users = userList, user = userList.first(), nextUser = nextUser)
            }
        }
    }

    fun swipeLeft() {
        viewModelScope.launch {
            _uiState.update { state ->
                val nextUser = getNextUser(state.users, state.user)
                val guessNextUser = getNextUser(state.users, nextUser)
                state.copy(user = nextUser, nextUser = guessNextUser)
            }
        }
    }

    fun swipeRight() {
        viewModelScope.launch {
            _uiState.update { state ->
                val nextUser = getNextUser(state.users, state.user)
                val guessNextUser = getNextUser(state.users, nextUser)
                state.copy(user = nextUser, nextUser = guessNextUser)
            }
        }
    }

    private fun getNextUser(users: List<User>, user: User): User {
        val currentIndex = users.indexOf(user)
        var nextUser = User()
        if ((users.size - 1) >= (currentIndex + 1)) {
            nextUser = users[currentIndex + 1]
        } else {
            if (users.isNotEmpty()) {
                nextUser = users[0]
            }
        }
        return nextUser
    }
}