package com.example.k2026_04_01_met_tour_start.pages

import android.util.Log
import com.example.k2026_04_01_met_tour_start.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.k2026_04_01_met_tour_start.models.SearchStrings
import com.example.k2026_04_01_met_tour_start.models.met_data.Department
import com.example.k2026_04_01_met_tour_start.models.met_data.MetDepartmentViewModel
import com.example.k2026_04_01_met_tour_start.models.met_data.MetObject
import com.example.k2026_04_01_met_tour_start.models.met_data.MetObjectsViewModel
import com.example.k2026_04_01_met_tour_start.models.met_data.getMetObjectsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

@Composable
fun LandingPage(
    goToDisplayPage: () -> Unit,
    goToHistoryPage: () -> Unit,
    modifier: Modifier
) {
    var searchText = remember { mutableStateOf("") }
    val searchStrings = SearchStrings()
    var metObjects: Flow<MetObject>
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp),
    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Text("Landing Screen", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        MetFrontDoor()
        InputSearchTerm( searchText =  searchText )
        Button(onClick = { searchStrings.addSearchTerm("${searchText.value}"); goToDisplayPage() },
            modifier.testTag("goto_display_button")) {

            Text("Display Page")
        }
        Button(onClick = { goToHistoryPage() },
            modifier.testTag("goto_history_button")) {
            Text("History Page")
        }

        GetMetObjects()
        DepartmentScreen()


    }
}

@Composable
fun MetFrontDoor() {
    Image(
        painter = painterResource(id = R.drawable.met_front_door),
        contentDescription = "MET front door",
        modifier = Modifier.fillMaxSize(0.25f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InputSearchTerm(searchText: MutableState<String>) {
    var text by remember { mutableStateOf("") }
    //val searchStrings: SearchStrings = SearchStrings()
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search term") },
            placeholder = { Text("Enter username") },
            singleLine = true
        )

        Text("Current value: $text")
        searchText.value = text
    }
}

@Composable
fun DepartmentScreen(viewModel: MetDepartmentViewModel = viewModel()) {
    val departments by viewModel.departments.collectAsState()

    LazyColumn {
        items(departments) { dept ->
            Text("${dept.departmentId}: ${dept.displayName}")
            Log.i("PGB","department: ${dept}")
        }
    }
}

@Composable
fun GetMetObjects(viewModel: MetObjectsViewModel = viewModel(), modifier: Modifier = Modifier) {
    val metObjects by viewModel.metObjects.collectAsState()

    LazyColumn {
        items(metObjects) { mObject ->
            Text("${mObject}: ${mObject}")
            Log.i("PGB","Met Object: ${mObject}")
        }
    }
}

@Composable
fun MetDepartmentCard(
    department: Department
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { Log.i("PGB","Department cards $department") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = department.displayName ?: "Unknown department",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}