package com.dranoer.abnamro.ui.util

import com.dranoer.abnamro.ui.util.Const.LIST_SCREEN

sealed class Route(val route: String) {
    object List : Route(LIST_SCREEN)
}