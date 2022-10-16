package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
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

    val handler = LocalUriHandler.current

    Column {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //region Avatar
            Image(
                modifier = Modifier.size(40.dp),
                painter = rememberAsyncImagePainter(item.avatar_url),
                contentDescription = null,
            ) //endregion
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.size_10)))
            //region Name
            Text(
                text = item.name,
                color = colorResource(id = R.color.black),
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            ) //endregion
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region FullName
        Text(
            text = stringResource(R.string.full_name, item.full_name),
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Description
        Text(
            text = stringResource(id = R.string.description),
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = item.description,
            color = colorResource(id = R.color.black),
            style = MaterialTheme.typography.caption,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Visibility
        IconItem(
            icon = Icons.Rounded.Home,
            text = stringResource(id = R.string.visibility_status, item.visibility),
            paddingEnd = 4,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Privacy
        IconItem(
            icon = Icons.Rounded.MoreVert,
            text = if (item.private) stringResource(R.string.privacy_private) else stringResource(R.string.privacy_public),
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region CTA button
        Button(
            onClick = { handler.openUri(item.html_url) }
        ) {
            Text(text = stringResource(id = R.string.open_repo))
        } //endregion
    }
}