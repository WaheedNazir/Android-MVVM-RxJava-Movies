package com.tmdb.movies.ui.movies

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.tmdb.movies.screen.TestMoviesScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class TestMoviesActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MoviesActivity::class.java)

    @Test
    fun shouldShowContents_whenLaunchingMoviesActivity() {
        Screen.onScreen<TestMoviesScreen> {
            content {
                isVisible()
            }
        }
    }


    @Test
    fun testContentItemsRecyclerView() {
        Screen.onScreen<TestMoviesScreen> {
            moviesRecyclerView {
                isVisible()
            }
        }
    }
}
