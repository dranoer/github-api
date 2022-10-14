package com.dranoer.abnamro.ui.util

import com.dranoer.abnamro.ui.util.Const.DETAIL_ARG_REPO_ID
import com.dranoer.abnamro.ui.util.Const.DETAIL_SCREEN
import com.dranoer.abnamro.ui.util.Const.LIST_SCREEN

sealed class Route(val route: String) {
    object List : Route(LIST_SCREEN)
    object Detail: Route("$DETAIL_SCREEN/{$DETAIL_ARG_REPO_ID}") {
        fun createRoute(repoId: Long) = "$DETAIL_SCREEN/$repoId"
    }
}