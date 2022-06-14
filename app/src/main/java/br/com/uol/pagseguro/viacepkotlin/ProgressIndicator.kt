package br.com.uol.pagseguro.viacepkotlin

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation

class ProgressIndicator {
    private val FADE_DURANTION = 500L

    private fun fadeIn(view: View) {
        val animation = AlphaAnimation(0f, 1f)
        animation.duration = FADE_DURANTION
        view.startAnimation(animation)
    }

    private fun fadeOut(view: View) {
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = FADE_DURANTION
        view.startAnimation(animation)
    }

    fun tradeView(view1: View, view2: View) {
        fadeOut(view1)
        Handler(Looper.getMainLooper()).postDelayed({
            view1.visibility = View.INVISIBLE
            view2.visibility = View.VISIBLE
            fadeIn(view2)
        }, 500L)
    }
}