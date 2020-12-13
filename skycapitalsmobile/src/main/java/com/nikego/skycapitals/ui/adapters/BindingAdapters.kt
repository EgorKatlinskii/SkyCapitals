package com.nikego.skycapitals.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import com.nikego.skycapitals.R
import com.nikego.skycapitals.data.CardType
import com.nikego.skycapitals.data.Currency
import com.nikego.skycapitals.vo.BankCardItem
import com.nikego.skycapitals.vo.ScoreItem

@BindingAdapter("app:currency", "app:scores")
fun TextView.setBalance(currency: Currency?, scores: List<ScoreItem>?) {
    if (currency != null && scores != null) {
        text = "${currency.sign} ${
            scores.filter {
                it.currency == currency
            }.sumOf { it.scoreBalance }
        }"
    }
}

@BindingAdapter("app:cardsAmount")
fun TextView.setCardsAmount(cards: List<BankCardItem>?) {
    cards?.let {
        text = cards.size.let {
            if (it == 1) "$it CARD" else "$it CARDS"
        }
    }
}

@BindingAdapter("app:currency", "app:balance")
fun TextView.setCurrencyBalance(currency: Currency?, balance: Int?) {
    if (currency != null && balance != null) {
        text = "${currency.sign} $balance"
    }
}

@BindingAdapter("cardType")
fun ImageView.setCardType(cardType: CardType?) {
    when (cardType) {
        CardType.VISA -> setImageResource(R.drawable.ic_visa)
        CardType.MASTERCARD -> setImageResource(R.drawable.ic_mastercard)
    }
}

@BindingAdapter("layoutFullscreen")
fun View.bindLayoutFullscreen(previousFullscreen: Boolean, fullscreen: Boolean) {
    if (previousFullscreen != fullscreen && fullscreen) {
        systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}

@BindingAdapter(
    "paddingLeftSystemWindowInsets",
    "paddingTopSystemWindowInsets",
    "paddingRightSystemWindowInsets",
    "paddingBottomSystemWindowInsets",
    requireAll = false
)
fun View.applySystemWindowInsetsPadding(
    previousApplyLeft: Boolean,
    previousApplyTop: Boolean,
    previousApplyRight: Boolean,
    previousApplyBottom: Boolean,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    if (previousApplyLeft == applyLeft &&
        previousApplyTop == applyTop &&
        previousApplyRight == applyRight &&
        previousApplyBottom == applyBottom
    ) {
        return
    }

    doOnApplyWindowInsets { view, insets, padding, _ ->
        val left = if (applyLeft) insets.systemWindowInsetLeft else 0
        val top = if (applyTop) insets.systemWindowInsetTop else 0
        val right = if (applyRight) insets.systemWindowInsetRight else 0
        val bottom = if (applyBottom) insets.systemWindowInsetBottom else 0

        view.setPadding(
            padding.left + left,
            padding.top + top,
            padding.right + right,
            padding.bottom + bottom
        )
    }
}

@BindingAdapter(
    "marginLeftSystemWindowInsets",
    "marginTopSystemWindowInsets",
    "marginRightSystemWindowInsets",
    "marginBottomSystemWindowInsets",
    requireAll = false
)
fun View.applySystemWindowInsetsMargin(
    previousApplyLeft: Boolean,
    previousApplyTop: Boolean,
    previousApplyRight: Boolean,
    previousApplyBottom: Boolean,
    applyLeft: Boolean,
    applyTop: Boolean,
    applyRight: Boolean,
    applyBottom: Boolean
) {
    if (previousApplyLeft == applyLeft &&
        previousApplyTop == applyTop &&
        previousApplyRight == applyRight &&
        previousApplyBottom == applyBottom
    ) {
        return
    }

    doOnApplyWindowInsets { view, insets, _, margin ->
        val left = if (applyLeft) insets.systemWindowInsetLeft else 0
        val top = if (applyTop) insets.systemWindowInsetTop else 0
        val right = if (applyRight) insets.systemWindowInsetRight else 0
        val bottom = if (applyBottom) insets.systemWindowInsetBottom else 0

        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            leftMargin = margin.left + left
            topMargin = margin.top + top
            rightMargin = margin.right + right
            bottomMargin = margin.bottom + bottom
        }
    }
}

fun View.doOnApplyWindowInsets(block: (View, WindowInsets, InitialPadding, InitialMargin) -> Unit) {
    val initialPadding = InitialPadding.recordForView(this)
    val initialMargin = InitialMargin.recordForView(this)

    setOnApplyWindowInsetsListener { v, insets ->
        block(v, insets, initialPadding, initialMargin)
        insets
    }

    requestApplyInsetsWhenAttached()
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(v: View?) {
                v?.removeOnAttachStateChangeListener(this)
                v?.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View?) = Unit
        })
    }
}


class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int) {

    companion object {
        fun recordForView(view: View) =
            view.run { InitialPadding(paddingLeft, paddingTop, paddingRight, paddingBottom) }
    }
}


class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int) {

    companion object {
        fun recordForView(view: View) =
            (view.layoutParams as? ViewGroup.MarginLayoutParams
                ?: throw IllegalArgumentException("invalid view layout params"))
                .run { InitialMargin(leftMargin, topMargin, rightMargin, bottomMargin) }
    }
}