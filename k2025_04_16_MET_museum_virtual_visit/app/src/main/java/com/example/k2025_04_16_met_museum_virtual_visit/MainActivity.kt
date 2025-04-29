package com.example.k2025_04_16_met_museum_virtual_visit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModelProvider
import coil3.compose.AsyncImage
import com.example.k2025_04_16_met_museum_virtual_visit.models.MuseumObject
import com.example.k2025_04_16_met_museum_virtual_visit.services.MuseumObjectService
import com.example.k2025_04_16_met_museum_virtual_visit.ui.theme.MuseumViewerTheme
import com.example.k2025_04_16_met_museum_virtual_visit.museumviewmodel.MuseumObjectViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var museumObjectViewModel =
            ViewModelProvider(this@MainActivity)[MuseumObjectViewModel::class.java]


        enableEdgeToEdge()
        setContent {
            MuseumViewerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MuseumObjectsList(museumObjectViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MuseumObjectsList(museumObjectViewModel: MuseumObjectViewModel) {

    val museumObjects by museumObjectViewModel.museumObjects.observeAsState(emptyList())
    val isFetchingNewMuseumObjects by museumObjectViewModel.isFetchingNewMuseumObjects.observeAsState(
        initial = true
    )

    var currentPage by rememberSaveable { mutableIntStateOf(1) }
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val selectedMuseumObject = rememberSaveable { mutableStateOf<MuseumObject?>(null) }

    if (museumObjects.isEmpty()) {
        Text(
            "Loading...",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 32.dp)
        )
    } else {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(16.dp)

        ) {
            item {
                Text(
                    "MET Gallery",
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 32.dp)
                )
            }

            items(museumObjects) { museumObject ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            brush = SolidColor(Color.LightGray),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            var imageLoading by rememberSaveable { mutableStateOf(true) }

                            AsyncImage(
                                model = museumObject.primaryImageSmall,
                                contentDescription = "$museumObject.name Image",
                                modifier = Modifier
                                    .height(48.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                onSuccess = { imageLoading = false },
                                onError = { imageLoading = false },
                                onLoading = { imageLoading = true }
                            )

                            if (imageLoading) {
                                Box(
                                    modifier = Modifier
                                        .height(48.dp)
                                        .width(48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 3.dp,
                                    )
                                }
                            }


                            Column {
                                Text(
                                    text = displayCorrectAttribute(museumObject.objectName),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = displayCorrectAttribute(museumObject.accessionYear),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            selectedMuseumObject.value = museumObject
                            showDialog.value = true
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFFC62828))

                    ) {
                        Text("View Details")
                    }

                }
            }

            item {
                Button(
                    onClick = {
                        currentPage++
                        museumObjectViewModel.getTopMuseumObjects(
                            currentPage
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFC62828)),
                    enabled = !isFetchingNewMuseumObjects
                ) {
                    Text(if (!isFetchingNewMuseumObjects) "Get Next 10 Objects" else "Loading...")
                }
            }
        }
    }

    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    selectedMuseumObject.value?.let { museumObject ->
                        var imageLoading by rememberSaveable { mutableStateOf(true) }

                        AsyncImage(
                            model = museumObject.primaryImageSmall,
                            contentDescription = "Image ${museumObject.objectID}",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)),
                            onSuccess = { imageLoading = false },
                            onError = { imageLoading = false },
                            onLoading = { imageLoading = true }
                        )

                        if (imageLoading) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    strokeWidth = 3.dp,
                                )
                            }
                        }

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedMuseumObject.value?.objectName ?: "",
                                fontWeight = FontWeight.Black,
                                fontSize = 24.sp
                            )
                        }

                        Column {
                            Text("Artist Name", fontWeight = FontWeight.Bold)
                            Text(displayCorrectAttribute(museumObject.artistDisplayName))
                        }

                        Column {
                            Text("Artist Bio", fontWeight = FontWeight.Bold)
                            Text(displayCorrectAttribute(museumObject.artistDisplayBio))
                        }

                        Column {
                            Text("Department", fontWeight = FontWeight.Bold)
                            Text(displayCorrectAttribute(museumObject.department))
                        }

                        Column {
                            Text("Year", fontWeight = FontWeight.Bold)
                            Text(displayCorrectAttribute(museumObject.accessionYear))
                        }

                        Column {
                            Text("ID", fontWeight = FontWeight.Bold)
                            Text(displayCorrectAttribute(museumObject.objectID.toString()))
                        }

                        Button(
                            onClick = { showDialog.value = false },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(Color(0xFFC62828))
                        ) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}


fun displayCorrectAttribute(attribute: String): String {
    if (attribute == "") {
        return "N/A"
    }

    return attribute
}