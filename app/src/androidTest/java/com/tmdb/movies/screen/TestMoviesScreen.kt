package com.tmdb.movies.screen

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.tmdb.movies.R
import org.hamcrest.Matcher

open class TestMoviesScreen : Screen<TestMoviesScreen>() {
    val content: KView = KView { withId(R.id.content) }

    val moviesRecyclerView: KRecyclerView =
        KRecyclerView({ withId(R.id.moviesRecyclerView) }, itemTypeBuilder = {
            itemType(::MainItem)
        })

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val image = KImageView(parent) { withId(R.id.image) }
        val circularProgressBar = KView(parent) { withId(R.id.circularProgressBar) }
        val percentage = KTextView(parent) { withId(R.id.percentage) }
        val title = KTextView(parent) { withId(R.id.title) }
        val release_date = KTextView(parent) { withId(R.id.release_date) }
    }
}
