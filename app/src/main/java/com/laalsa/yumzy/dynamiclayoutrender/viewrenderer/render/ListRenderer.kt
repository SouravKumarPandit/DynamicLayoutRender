package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render

import android.content.Context
import android.view.View
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template.IItemDTO

class ListRenderer : IListRenderer {
    override fun getViewAtIndex(
        context: Context,
        clItemDTO: IItemDTO?,
        iViewIndex: Int,
        iViewId: Int
    ): View? {
        return null
    }

    override fun setValue(
        context: Context,
        clItemDTO: IItemDTO?,
        clView: View,
        objValue: Any?,
        objExtraValues: Array<Any?>?
    ) {

    }
}