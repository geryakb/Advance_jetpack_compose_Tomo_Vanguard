package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tomovanguard_tugas.RoomDB.Data
import com.example.tomovanguard_tugas.ui.assets.Button.TomoButton
import kotlinx.coroutines.job

@Composable
fun RoomAlertDialog(
    modifier: Modifier = Modifier,
    openDialog: Boolean,
    onClose: () -> Unit,
    viewModel : RoomViewModel = hiltViewModel()
){
    var text by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val data = Data(0, text, description)
    val focusRequester = FocusRequester()
    val context = LocalContext.current

    if (openDialog){
        AlertDialog(
            title = {
                    Text(text = "Data")
            },
            text = {
                   LaunchedEffect(key1 = true, block = {
                       coroutineContext.job.invokeOnCompletion {
                           focusRequester.requestFocus()
                       }
                   })

                Column(
                    modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        placeholder = {
                            Text(text = "Apa yang kamu lakukan hari ini ?")
                        },
                        shape = RectangleShape,
                        modifier = modifier
                            .focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        placeholder = {
                            Text(text = "Deskripsikan")
                        },
                        shape = RectangleShape,
                        modifier = modifier
                            .focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                    )
                }
            },
            onDismissRequest = {
                               onClose()
                text = ""
                description = ""
            },
            confirmButton = {
                TomoButton(buttonTitle = "Atur",
                    onClicked = {
                        if (text.isNotBlank() || description.isNotBlank()){
                            viewModel.insertData(data)
                            text = ""
                            description = ""
                            onClose()
                        } else {
                            Toast.makeText(context, "Judul Atau Deskripsi Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show()
                        }
                    },
                    width = 150.dp,
                    height = 70.dp)
            })
    }
}