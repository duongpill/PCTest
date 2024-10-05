package com.duongnh.pctest.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.duongnh.pctest.domain.model.User

@Composable
fun UserCard(user: User) {
    Card(
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SubcomposeAsyncImage(
                model = user.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(800f / 1200f),
                loading = {
                    ProgressBar()
                },
                contentDescription = null
            )
            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "${user.age}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = user.bio,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ProgressBar(){
    val strokeWidth = 5.dp

    Column(
        modifier = Modifier.size(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}