package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.dranoer.abnamro.R
import com.dranoer.abnamro.data.model.RepoEntity
import com.dranoer.abnamro.ui.theme.GithubRepoTheme

@ExperimentalCoilApi
@Composable
fun DetailItem(item: RepoEntity) {

    val handler = LocalUriHandler.current

    Column {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
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
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            ) //endregion
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_40)))
        //region FullName
        IconItem(
            icon = Icons.Rounded.KeyboardArrowRight,
            text = item.full_name,
            paddingEnd = 5,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Description
        IconItem(
            icon = Icons.Rounded.KeyboardArrowRight,
            text = item.description,
            paddingEnd = 5,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Visibility
        IconItem(
            icon = Icons.Rounded.KeyboardArrowRight,
            text = stringResource(id = R.string.visibility_status, item.visibility),
            paddingEnd = 5,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region Privacy
        IconItem(
            icon = Icons.Rounded.KeyboardArrowRight,
            text = if (item.private) stringResource(R.string.privacy_private) else stringResource(R.string.privacy_public),
            paddingEnd = 5,
        ) //endregion
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_30)))
        //region CTA button
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { handler.openUri(item.html_url) }
        ) {
            Text(text = stringResource(id = R.string.open_repo))
        } //endregion
    }
}

//region Preview
@Preview("Normal detail item")
@Composable
private fun PreviewDetailItem_Normal() {
    GithubRepoTheme {
        DetailItem(
            item = RepoEntity(
                id = 12345,
                name = "Normal name",
                full_name = "Normal preview fullname",
                description = "This is normal preview description.",
                avatar_url = "",
                visibility = "private",
                private = false,
            )
        )
    }
}
//endregion