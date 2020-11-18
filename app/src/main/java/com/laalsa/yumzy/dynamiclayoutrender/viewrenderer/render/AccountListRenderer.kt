package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.IListRenderer

class AccountListRenderer : IListRenderer {
    override fun getViewAtIndex(
        context: Context,
        clItemDTO: IItemDTO?,
        iViewIndex: Int,
        iViewId: Int
    ): View? {
        if (iViewIndex == 1) {
            val textView = TextView(context)
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            textView.text = clItemDTO?.value
            textView.setBackgroundColor(Color.RED)
            textView.textSize = 10f
            textView.gravity = Gravity.CENTER
            return textView

        }
        return null
    }

    override fun setValue(
        context: Context,
        clItemDTO: IItemDTO?,
        clView: View,
        objValue: Any?,
        objExtraValues: Array<Any?>?
    ) {

        if (objValue != null) {
            if (clView is TextView) {
                if (clItemDTO != null) {
                    clView.text = clItemDTO.value
                } else {
                    clView.text = objValue.toString()
                }
            } else if (clView is ImageView) {


            }
        }
    }
}