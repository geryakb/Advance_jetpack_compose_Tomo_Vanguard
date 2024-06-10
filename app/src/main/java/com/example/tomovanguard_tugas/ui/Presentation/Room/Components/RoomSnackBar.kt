package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun RoomSnackBar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    msg: String,
    actionlabel: String,
    onAction: () -> Unit
){
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val snackbarResult = snackbarHostState.showSnackbar(
            message = msg,
            actionLabel = actionlabel,
            duration = SnackbarDuration.Short
        )

        when(snackbarResult){
            SnackbarResult.Dismissed -> {}
            SnackbarResult.ActionPerformed -> {
                onAction()
            }
        }
    }
}