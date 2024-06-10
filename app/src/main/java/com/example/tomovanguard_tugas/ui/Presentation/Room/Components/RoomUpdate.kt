package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.ui.assets.Button.TomoButton
import com.example.tomovanguard_tugas.ui.assets.TextField.TomoTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomUpdate(
    modifier: Modifier = Modifier,
    id : Int,
    viewModel: RoomViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.icon_edit))
    val logoAnimationState =
        animateLottieCompositionAsState(composition = composition, iterations = Int.MAX_VALUE)
    var task = viewModel.data.task
    var description = viewModel.data.description

    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.getDataById(id = id)
        }
    )
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Detail Screen")
            },
                navigationIcon = {
                    IconButton(onClick = { onBack()}) {
                        Icon(imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ){paddingValues ->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            LottieAnimation(composition = composition,
                progress = { logoAnimationState.progress },
                modifier.size(352.dp))
            TomoTextField(
                value = task,
                onChangeValue = {
                                viewModel.updateTask(newValue = it)
                },
                label = "Task",
                placeholder = "Task",
                height = 70.dp,
                width = 300.dp
            )
            OutlinedTextField(
                value = description,
                onValueChange = {
                    viewModel.updateDescription(newValue = it)
                },
                modifier = modifier
                    .height(70.dp)
                    .width(300.dp)
                    .focusRequester(FocusRequester()),
                placeholder = {
                    Text(text = "Description")
                },
                label = {
                    Text(text = "Description")
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done
                )
            )
            TomoButton(buttonTitle = "Ubah",
                onClicked = {
                            viewModel.updateData(viewModel.data)
                    onBack()
                },
                width = 300.dp,
                height = 70.dp)
        }
    }
}