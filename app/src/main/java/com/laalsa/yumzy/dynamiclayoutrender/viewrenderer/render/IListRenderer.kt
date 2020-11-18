package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render

import android.content.Context
import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO
import android.view.View

interface IListRenderer {
    fun getViewAtIndex(
        context: Context,
        clReportColumnDTO: IItemDTO?,
        iViewIndex: Int,
        iViewId: Int
    ): View?

    fun setValue(
        context: Context,
        clReportColumnDTO: IItemDTO?,
        clView: View,
        objValue: Any?,
        objExtraValues: Array<Any?>?
    )
}