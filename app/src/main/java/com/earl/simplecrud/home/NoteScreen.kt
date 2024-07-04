package com.earl.simplecrud.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoteScreen(
    onDoneEditing: () -> Unit,
    onNavUp: () -> Unit
) {
    Scaffold(
        topBar = {
            NoteAppBar(onDoneEditing = onDoneEditing, onNavUp = onNavUp)
        }
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {

        }
    }
}

@Composable
fun NoteAppBar(
    onDoneEditing: () -> Unit,
    onNavUp: () -> Unit
){
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        IconButton(modifier = Modifier.weight(1f), onClick = onNavUp) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Text(text = "SomeText", modifier = Modifier.weight(3f), style = MaterialTheme.typography.displaySmall)
        IconButton(onClick = onDoneEditing, modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "Done"
            )
        }

    }
}

@Preview
@Composable
fun NoteScreenPreview() {
    NoteScreen(onDoneEditing = { /*TODO*/ }, {})
}

