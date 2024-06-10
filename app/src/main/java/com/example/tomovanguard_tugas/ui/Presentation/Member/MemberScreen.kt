package com.example.tomovanguard_tugas.ui.Presentation.Member

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tomovanguard_tugas.MemberRepository.MemberViewModel
import com.example.tomovanguard_tugas.Route.Screen
import com.example.tomovanguard_tugas.ui.Presentation.Member.Components.MemberLayout

@Composable
fun MemberScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){
    val viewModel = viewModel<MemberViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val person by viewModel.person.collectAsState()

    Box(
        modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        Column(
            modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(modifier = modifier.height(10.dp)
            )
            OutlinedTextField(value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier.fillMaxWidth(),
                placeholder = { Text(text = "Cari...")}
            )
            LazyColumn {
                items(
                    items = person, key = { it.id },
                    itemContent = { member ->
                        Card(
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            MemberLayout(
                                onItemClicked = { memberId ->
                                    navController.navigate(Screen.MemberDetail.route + "/$memberId")
                                },
                                member = member
                            )
                        }
                    }
                )
            }
        }
    }
}
