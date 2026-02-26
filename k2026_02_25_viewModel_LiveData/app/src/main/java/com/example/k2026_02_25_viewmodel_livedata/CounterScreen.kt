package com.example.k2026_02_25_viewmodel_livedata

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CounterScreen(vm: CounterViewModel = viewModel()) {
    val count by vm.count.observeAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Count = $count", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = vm::dec) { Text("−") }
            Button(onClick = vm::inc) { Text("+") }
        }
    }
}
