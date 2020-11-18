package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template

import android.annotation.SuppressLint
import android.content.Context
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.ListRenderer

@SuppressLint("ViewConstructor")
class DynamicLayoutTemplate constructor(
    context: Context,
    totalIndex: Int,
    layoutDimension: IntArray = intArrayOf(2, 1, 2)
) :
    ItemTemplate(context, totalIndex, layoutDimension, ListRenderer())