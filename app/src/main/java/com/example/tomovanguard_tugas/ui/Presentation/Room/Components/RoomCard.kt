package com.example.tomovanguard_tugas.ui.Presentation.Room.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.tomovanguard_tugas.R
import com.example.tomovanguard_tugas.RoomDB.Data

@Composable
fun RoomCard(
    modifier: Modifier = Modifier,
    data: Data,
    onAction : () -> Unit,
    onUpdate: (id: Int) -> Unit
){
    Card(
        modifier
            .fillMaxWidth()
            .clickable {
                onUpdate(data.id)
            }
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = { onAction() },
                modifier
                    .weight(1f)
            ) {
                Icon(painter = painterResource(id = R.drawable.check_icon),
                    contentDescription = null,
                    modifier.size(35.dp)
                )
            }
            Text(text = data.task,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .weight(6f)
            )
        }
    }
}