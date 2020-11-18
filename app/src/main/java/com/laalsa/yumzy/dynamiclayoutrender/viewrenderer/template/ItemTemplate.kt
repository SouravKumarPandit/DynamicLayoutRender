package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.TextViewCompat
import com.facebook.shimmer.ShimmerFrameLayout
import com.laalsa.yumzy.dynamiclayoutrender.R
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.CLViewIdGenerator.generateNewId
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.data.CLItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.IListRenderer
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.render.ListRenderer

abstract class ItemTemplate constructor(
    context: Context,
    final override val viewLength: Int,
    final override val layoutDimensions: IntArray,
    final override val listRenderer: IListRenderer
) : FrameLayout(context, null, 0),
    IItemTemplate {
    private val shimmerFrameLayout: ShimmerFrameLayout = ShimmerFrameLayout(context)
    override val arrTxtViewIds:IntArray = IntArray(viewLength)

    init {
        inflateListItemView()

    }
    @Suppress("unused")
    private constructor(context: Context) : this(context, null, 0)

    @Suppress("unused")
    private constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @Suppress("unused")
    private constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        3,
        intArrayOf(1, 1, 1), ListRenderer()
    )
/*
    private val bd by lazy(LazyThreadSafetyMode.NONE) {
        val layoutInflater = LayoutInflater.from(context)
        LayoutTemplateBinding.inflate(layoutInflater, this, true)
    }*/


    open val padding: Int = 5
    open val textPadding: Int = 0
    open var iViewIndex = 0


    override fun getView(
        iRowIndex: Int,
        iColIndex: Int,
        id: Int,
        iControlType: Byte,
        layoutGravity: ChildPosition?
    ): View {
        val txtView = TextView(context)
        txtView.isSingleLine = true
        txtView.id = id
        txtView.ellipsize = TextUtils.TruncateAt.END
        TextViewCompat.setTextAppearance(
            txtView,
            getTextStyleable(iViewIndex)
        )
        txtView.setPadding(textPadding, textPadding, textPadding, textPadding)
        when (layoutGravity) {
            ChildPosition.START -> txtView.gravity = Gravity.START
            ChildPosition.MIDDLE -> txtView.gravity = Gravity.CENTER
            ChildPosition.END -> txtView.gravity = Gravity.END
        }
        return txtView
    }


    final override fun inflateListItemView() {
        val arrayOfItemDTO = defaultItemList()

        this.id = R.id.template_layout
        this.setPadding(padding, padding, padding, padding)


        val clLinearLayout = LinearLayout(context)
        clLinearLayout.orientation = LinearLayout.VERTICAL

        for (i in layoutDimensions.indices) {
            val iLayoutDimension = layoutDimensions[i]
            val clRowLinearLayout = getRowLayout()
            var iViewsLength = 0
            iViewsLength += iLayoutDimension
            val iReportColsLength = arrayOfItemDTO.size
            var j = 0
            while (j < iLayoutDimension && (iReportColsLength == 0 || iViewsLength <= iReportColsLength && iReportColsLength > iViewIndex)) {
                var iControlType: Byte = -1
                var clItemDTO: IItemDTO? = null
                if (iReportColsLength > 0) {
                    clItemDTO = arrayOfItemDTO[iViewIndex]
                    iControlType = clItemDTO?.controlType ?: iControlType
                }
                if (arrTxtViewIds[iViewIndex] == 0) {
                    arrTxtViewIds[iViewIndex] = generateNewId()
                }
                var childView = listRenderer.getViewAtIndex(
                    context,
                    clItemDTO,
                    iViewIndex,
                    arrTxtViewIds[iViewIndex]
                )
                if (childView == null) {
                    val layoutGravity = when (j) {
                        0 -> ChildPosition.START
                        iLayoutDimension - 1 -> ChildPosition.END
                        else -> ChildPosition.MIDDLE
                    }
                    childView =
                        getView(i, j, arrTxtViewIds[iViewIndex], iControlType, layoutGravity)

                    childView.layoutParams = LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT, 1f
                    )

                }
                childView.background =
                    getRoundedDrawable(
                        shimmerColor = 0xFFDEDEDE.toInt(),
                        viewIndex = iViewIndex,
                        totalRow = j
                    )
                iViewIndex++
                clRowLinearLayout.addView(childView)
                j++
            }

            clLinearLayout.addView(clRowLinearLayout)
        }
//        shimmerFrameLayout.layoutParams=ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        shimmerFrameLayout.addView(clLinearLayout)
        this.addView(shimmerFrameLayout)
        iViewIndex = 0
    }

    open fun defaultItemList(): Array<IItemDTO?> {
        return Array(viewLength) {
            return@Array CLItemDTO()
        }
    }

    override fun setValue(
        iViewIndex: Int,
        itemDTO: IItemDTO?,
        obj: Any?,
        objAry: Array<Any?>?
    ) {
        val findViewById = this.findViewById<View>(iViewIndex)
        listRenderer.setValue(context, itemDTO, findViewById, obj, objAry)
    }

    open fun getTextStyleable(iViewIndex: Int): Int {
        return when (iViewIndex) {
            1 -> R.style.TextAppearance_AppCompat_Widget_Button_Borderless_Colored
            else -> R.style.TextAppearance_MaterialComponents_Headline6
        }

    }

    fun setTemplateValue(itemList: Array<IItemDTO?>) {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.clearAnimation()
        shimmerFrameLayout.setShimmer(null)


        for ((viewIndex, viewId) in arrTxtViewIds.withIndex()) {
            val findViewById = this.findViewById<View>(viewId)
            findViewById?.let {
                findViewById.background = null
                if (it is TextView) {
                    if (itemList.size > viewIndex) {
                        val iItemDTO = itemList[viewIndex]
                        if (iItemDTO != null) {
                            it.text = iItemDTO.text
                        }
                    } else
                        it.visibility = View.GONE
                }
            }
        }
    }


    override fun getRowLayout(): ViewGroup {
        val clRowLinearLayout = LinearLayout(context)
        clRowLinearLayout.orientation = LinearLayout.HORIZONTAL
        return clRowLinearLayout
    }


    open fun getRoundedDrawable(
        shimmerColor: Int = 0xFFDEDEDE.toInt(),
        viewIndex: Int,
        totalRow: Int
    ): Drawable? {
        val iRadius = 8f
        val endPadding = if (viewIndex % 2 == 0) 100 else 50
        val fArrRightOuterRadius =
            floatArrayOf(iRadius, iRadius, iRadius, iRadius, iRadius, iRadius, iRadius, iRadius)
        val clRightShape = ShapeDrawable(RoundRectShape(fArrRightOuterRadius, null, null))
        clRightShape.paint.color = shimmerColor
        val clLayerDrawable = LayerDrawable(arrayOf<Drawable>(clRightShape))
        clLayerDrawable.setLayerInset(0, 15, 0, endPadding, 20)
        return clLayerDrawable
    }
}