package com.example.k2026_04_09_myviewmodelfactoryunittesting.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun GetMetObjects(
    getNumbers: suspend () -> List<Int>,
    modifier: Modifier = Modifier
) {
    val vm: MetObjectsViewModel = viewModel(
        factory = MetObjectsViewModelFactory(getNumbers)
    )

    val metObjects by vm.metObjects.collectAsState()

    // use metObjects in UI
}
