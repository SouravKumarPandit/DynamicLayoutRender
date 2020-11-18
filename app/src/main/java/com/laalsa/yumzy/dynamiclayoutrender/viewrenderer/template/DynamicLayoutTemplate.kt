package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template

import android.annotation.SuppressLint
import android.content.Context
import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.ListRenderer

@SuppressLint("ViewConstructor")
class DynamicLayoutTemplate(context: Context) :
    ItemTemplate(context, ListRenderer()) {

    override val viewLength: Int = 5
    override val layoutDimensions: IntArray = intArrayOf(1,1, 1,2)
    override val arrTxtViewIds: IntArray = intArrayOf(121, 55, 65, 568, 64)
    }