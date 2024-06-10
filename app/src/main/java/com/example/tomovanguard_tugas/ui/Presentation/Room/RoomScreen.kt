package com.example.tomovanguard_tugas.ui.Presentation.Room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tomovanguard_tugas.SharedPreferences.SharedPreferencesManager
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomAlertDialog
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomCard
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomEmptyTasktScreen
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomSnackBar
import com.example.tomovanguard_tugas.ui.Presentation.Room.Components.RoomViewModel

@Composable
fun RoomScreen(
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = hiltViewModel(),
    onUpdate: (id : Int) -> Unit
){
    val data by viewModel.getAllData.collectAsState(initial = emptyList())
    var openDialog by remember { mutableStateOf(false ) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                openDialog = true
            }) {
                Icon(imageVector = Icons.Rounded.Add,
                    contentDescription = null
                )
            }
        }
    ){paddingValues ->
        RoomAlertDialog(openDialog = openDialog, onClose = { openDialog = false })

        if (data.isEmpty()) {
            RoomEmptyTasktScreen(paddingValues = paddingValues)
        } else {
            LazyColumn(
                modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(
                    items = data,
                    key = {it.id}
                ){data ->
                    RoomCard(data = data,
                        onAction = { viewModel.deleteData(data)
                            RoomSnackBar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                msg = "DONE! -> ${data.task}",
                                actionlabel = "UNDO",
                                onAction = {
                                    viewModel.undoDeletedData()
                                }
                            )
                        },
                        onUpdate = onUpdate
                    )
                }
            }
        }
    }
}