package com.example.k2026_02_25_viewmodel_text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyScreen(vm: TextViewModel = viewModel()) {
    val text by vm.text.observeAsState(initial = "")

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { it:String -> vm.setText(it) },
            label = { Text("Enter text") }
        )

        Spacer(Modifier.height(12.dp))
        Text("ViewModel says: $text")
    }
}

