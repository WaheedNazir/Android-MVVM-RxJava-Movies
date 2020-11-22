package com.tmdb.movies.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    protected fun changeTitle(title: String, subtitle: String = "") {
        supportActionBar?.let {
            it.title = title
            it.subtitle = subtitle
        }
    }

    protected fun replaceFragment(layoutId: Int, fragment: Fragment, stack: Boolean = true) {
        changeFragment(layoutId, fragment, stack, true)
    }

    protected fun addFragment(layoutId: Int, fragment: Fragment, stack: Boolean = true) {
        changeFragment(layoutId, fragment, stack, false)
    }

    private fun changeFragment(
        layoutId: Int,
        fragment: Fragment,
        stack: Boolean = true,
        replace: Boolean
    ) {
        supportFragmentManager.beginTransaction().run {
            if (stack) {
                addToBackStack(fragment::class.java.name)
            }
            if (replace) replace(layoutId, fragment) else add(layoutId, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}