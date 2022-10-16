package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dranoer.abnamro.R

@Composable
fun IconItem(icon: ImageVector, text: String, paddingEnd: Int = 0) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, Modifier.padding(end = paddingEnd.dp))
        Text(
            text = text,
            color = colorResource(id = R.color.dark_grey),
            style = MaterialTheme.typography.subtitle2,
        )
    }
}