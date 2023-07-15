package com.xorko.pokedex.common.domain

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

/**
 * Repository for application resources.
 */
class ResourceRepository(private val context: Context) {

    fun getString(@StringRes resId: Int, vararg args: String) = context.getString(resId, *args)

    fun getDrawable(@DrawableRes resId: Int) = AppCompatResources.getDrawable(context, resId)

    fun getColor(@ColorRes resId: Int) = context.getColor(resId)
}