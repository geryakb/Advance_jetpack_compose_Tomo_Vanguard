package com.example.tomovanguard_tugas.ui.Presentation.Member.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tomovanguard_tugas.MemberRepository.Member

@Composable
fun MemberLayout(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    member: Member
){

    Card(
        modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onItemClicked(member.id)
            },
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ){
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(7.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = modifier
                    .fillMaxSize()
            ){
                memberFace(member = member)
                Column(
                    modifier
                        .fillMaxSize()
                        .padding(7.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.Start
                ){
                    Text(text = "Nama : ${member.name}",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun memberFace(member: Member) {
    Card(
        modifier = Modifier
            .size(100.dp)
            .border(0.5.dp, Color(0xFF2196F3), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    ){
        Image(
            painter = painterResource(id = member.image),
            contentDescription = member.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
    }
}
