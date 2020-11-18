package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template

import android.annotation.SuppressLint
import android.content.Context
import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.ListRenderer

@SuppressLint("ViewConstructor")
class DynamicLayoutTemplate(context: Context) :
    ItemTemplate(context, ListRenderer()) {

    override val viewLength
            get() =  5
    override val layoutDimensions
            get() = intArrayOf(2, 1,2)
    override val arrTxtViewIds
            get() = intArrayOf(121, 55, 65, 568, 64)
    }