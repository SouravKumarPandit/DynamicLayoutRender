package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template

import android.view.View
import android.view.ViewGroup
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.IListRenderer

interface IItemTemplate {
    val viewLength: Int
    val layoutDimensions: IntArray
    val listRenderer:IListRenderer
    fun getRowLayout(): ViewGroup?
    fun getView(
        iRowIndex: Int,
        iColIndex: Int,
        id: Int,
        iControlType: Byte,
        layoutGravity: ChildPosition?
    ): View

    fun inflateListItemView(/*arrayOfItemDTO: Array<IItemDTO?>*/)
    val arrTxtViewIds: IntArray
    fun setValue(
        iViewIndex: Int,
        itemDTO: IItemDTO?,
        obj: Any?,
        objAry: Array<Any?>?
    )
}