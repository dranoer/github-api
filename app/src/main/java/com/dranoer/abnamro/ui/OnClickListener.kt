package com.dranoer.abnamro.ui

import com.dranoer.abnamro.data.model.Repo

class OnClickListener(val clickListener: (repo: Repo) -> Unit) {
    fun onClick(repo: Repo) =
        clickListener(repo)
}