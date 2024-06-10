package com.example.tomovanguard_tugas.ui.Presentation.Alarm

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tomovanguard_tugas.AlarmManager.scheduleNotification
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.ui.Presentation.Alarm.Components.AlarmTextField
import com.example.tomovanguard_tugas.ui.Presentation.Alarm.Components.TimePickerDialog
import com.example.tomovanguard_tugas.ui.assets.Button.TomoButton
import com.example.tomovanguard_tugas.ui.assets.TextField.TomoTextField
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val context = LocalContext.current
    val date = remember { Calendar.getInstance().timeInMillis }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.alarmwallp_icon))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition, iterations = Int.MAX_VALUE)
    var scheduleText by remember { mutableStateOf("") }
    var scheduleDate by remember { mutableStateOf("") }
    var scheduleTime by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date)
    var showDatePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    Scaffold {paddingValues ->
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val selectedDate = Calendar.getInstance().apply {
                            timeInMillis = datePickerState.selectedDateMillis!!
                        }
                        scheduleDate = formatter.format(selectedDate.time)
                        showDatePicker = false
                    }) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        if (showTimePicker){
            TimePickerDialog(
                onDissmissRequest = { showTimePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        scheduleTime = "${timePickerState.hour}: ${timePickerState.minute}"
                        showTimePicker = false
                    }) {
                        Text(text = "OK")
                    }
                },
                dissmissButton = {
                    TextButton(onClick = { showTimePicker = false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                TimePicker(state = timePickerState)
            }
        }
        Surface(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment =Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Spacer(modifier = modifier.height(16.dp))
                LottieAnimation(composition = composition,
                    progress = { logoAnimationState.progress },
                    modifier
                        .size(250.dp)
                )
                TomoTextField(
                    value = scheduleText,
                    onChangeValue = {
                                    scheduleText = it
                    },
                    label = "Alarm Title",
                    placeholder = "Alarm Title",
                    height = 70.dp,
                    width = 400.dp
                )
                AlarmTextField(
                    value = scheduleDate,
                    onValueChange = {
                                    scheduleDate = it
                    },
                    onClicked = {
                                showDatePicker = true
                    },
                    trailingIconResId = R.drawable.date_icon,
                    placeholder = "Date",
                    label = "Date"
                )
                AlarmTextField(
                    value = scheduleTime,
                    onValueChange = {
                                    scheduleTime = it
                    },
                    onClicked = {
                                showTimePicker = true
                    },
                    trailingIconResId = R.drawable.clock_icon,
                    placeholder = "Time",
                    label = "Time"
                )
                TomoButton(
                    buttonTitle = "Atur",
                    onClicked = {
                        if (scheduleText.isBlank() || scheduleDate.isBlank() || scheduleTime.isBlank()) {
                            Toast.makeText(
                                context,
                                "Semua field wajib diisi!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            scheduleNotification(
                                context,
                                timePickerState,
                                datePickerState,
                                scheduleText
                            )
                            scheduleText = ""
                            scheduleDate = ""
                            scheduleTime = ""
                        }
                },
                    width = 300.dp,
                    height = 70.dp
                )
            }
        }
    }
}