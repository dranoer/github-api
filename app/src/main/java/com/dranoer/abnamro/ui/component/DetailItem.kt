package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.RepoEntity

@ExperimentalCoilApi
@Composable
fun DetailItem(item: RepoEntity) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        //region Avatar
        Image(
            modifier = Modifier.size(50.dp),
            painter = rememberAsyncImagePainter(item.avatar_url),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(12.dp)) //endregion
        //region Info
        Column() {
            //region Name
            Text(
                text = item.name ?: "",
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp)) //endregion
            //region Status
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //region Visibility
                Icon(Icons.Rounded.Home, "Visibility status", Modifier.padding(end = 4.dp))
                Text(
                    text = stringResource(
                        id = R.string.visibility_status,
                        item.visibility.toString()
                    ),
                    color = colorResource(id = R.color.dark_grey),
                    style = MaterialTheme.typography.subtitle2,
                )
                Spacer(modifier = Modifier.width(20.dp)) //endregion
                //region Privacy
                Icon(Icons.Rounded.MoreVert, "Privacy status")
                Text(
                    text = if (item.private == true) stringResource(R.string.privacy_private) else stringResource(
                        R.string.privacy_public
                    ),
                    color = colorResource(id = R.color.dark_grey),
                    style = MaterialTheme.typography.subtitle2,
                ) //endregion
            } //endregion
        } //endregion
    }
}