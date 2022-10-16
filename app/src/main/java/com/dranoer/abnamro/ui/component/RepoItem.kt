package com.dranoer.abnamro.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.dranoer.abnamro.data.model.Repo
import com.dranoer.abnamro.ui.OnClickListener
import com.dranoer.abnamro.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.abnamro.data.model.Owner
import com.dranoer.abnamro.ui.theme.GithubRepoTheme

@ExperimentalCoilApi
@Composable
fun RepoItem(item: Repo, callback: OnClickListener?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { callback?.onClick(item) },
        shape = RoundedCornerShape(16),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            //region Avatar
            Image(
                modifier = Modifier.size(50.dp),
                painter = rememberAsyncImagePainter(item.owner?.avatar_url),
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
                    IconItem(
                        icon = Icons.Rounded.KeyboardArrowRight,
                        text = stringResource(
                            id = R.string.visibility_status,
                            item.visibility.toString()
                        ),
                    )
                    Spacer(modifier = Modifier.width(20.dp)) //endregion
                    //region Privacy
                    IconItem(
                        icon = Icons.Rounded.KeyboardArrowRight,
                        text = if (item.private == true) stringResource(R.string.privacy_private) else stringResource(
                            R.string.privacy_public
                        ),
                    ) //endregion
                } //endregion
            } //endregion
        }
    }
}

//region Preview
@Preview("Normal repo item")
@Composable
private fun PreviewRepoItem_Normal() {
    GithubRepoTheme() {
        RepoItem(
            item = Repo(
                id = 12345,
                name = "Normal preview name",
                owner = Owner(""),
                visibility = "Visible",
                private = true,
            ),
            callback = null,
        )
    }
}

@Preview("Repo item with long string")
@Composable
private fun PreviewRepoItem_LongName() {
    GithubRepoTheme() {
        RepoItem(
            item = Repo(
                id = 12345,
                name = "I'm a very very very very very very very very very very very very very very long name",
                owner = Owner(""),
                visibility = "Visible",
                private = true,
            ),
            callback = null,
        )
    }
}

@Preview("Repo item with no privacy or visibility status defined")
@Composable
private fun PreviewRepoItem_EmptyInputs() {
    GithubRepoTheme() {
        RepoItem(
            item = Repo(
                id = 12345,
                name = "EmptyInputs preview name",
                owner = Owner(""),
                visibility = null,
                private = null,
            ),
            callback = null,
        )
    }
} //endregion