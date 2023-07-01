package com.example.wallnoire.utils

import android.R
import android.content.Context
import android.text.TextUtils
import android.util.TypedValue
import org.jetbrains.annotations.NonNls
import java.util.Locale

@NonNls
object ThemeUtil {
    fun pxToDp(context: Context, pixel: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return if (pixel < 0) pixel else Math.round(pixel / displayMetrics.density)
    }

    fun dpToPx(context: Context, dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return if (dp < 0) dp else Math.round(dp * displayMetrics.density)
    }

    fun dpToPx(context: Context, dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }

    fun pxToSp(context: Context, pixel: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return if (pixel < 0) pixel else Math.round(pixel / displayMetrics.scaledDensity)
    }

    fun spToPx(context: Context, sp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp.toFloat(), displayMetrics)
            .toInt()
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = getAndroidIdentifier(context, "dimen", "status_bar_height")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        //Logger.v("statusBar height: " + result);
        return result
    }

    fun getNavigationBarHeight(context: Context): Int {
        var result = 0
        val resourceId = getAndroidIdentifier(context, "dimen", "navigation_bar_height")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getActionBarHeight(context: Context): Int {
        val typedValue = TypedValue()
        var actionBarHeight = 0
        if (context.theme.resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            val displayMetrics = context.resources.displayMetrics
            val actionBar = TypedValue.complexToDimension(typedValue.data, displayMetrics)
            actionBarHeight = Math.round(actionBar)
        }
        return actionBarHeight
    }

    fun getDimenIdentifier(context: Context, name: String): Int {
        return getIdentifier(context, "dimen", name)
    }

    fun getStringIdentifier(context: Context, name: String): Int {
        return getIdentifier(context, "string", name)
    }

    fun getIdentifier(context: Context, @NonNls defType: String?, name: String): Int {
        return if (TextUtils.isEmpty(name)) {
            0
        } else context.resources.getIdentifier(
            name.lowercase(Locale.getDefault()),
            defType,
            context.packageName
        )
    }

    fun getAndroidIdentifier(
        context: Context,
        @NonNls defType: String?,
        @NonNls name: String
    ): Int {
        return if (TextUtils.isEmpty(name)) {
            0
        } else context.resources.getIdentifier(
            name.lowercase(Locale.getDefault()),
            defType,
            "android"
        )
    }
}
