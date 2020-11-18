package com.laalsa.yumzy.dynamiclayoutrender

import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.databinding.FragmentFirstBinding
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.SampleItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template.DynamicLayoutTemplate


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null
    var numberList = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    private val binding get() = _binding!!
    private var totalItems: Int = 5
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val aa =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, numberList)
        binding.totalSpinner.adapter = aa
        binding.totalSpinner.onItemSelectedListener = this
        binding.btnAddLayout.setOnClickListener {
            addLayoutToContainer(5)
        }
    }

    private fun addLayoutToContainer(totalIndex: Int) {
        val dynamicLayoutTemplate = DynamicLayoutTemplate(requireContext())
        val itemList = Array<IItemDTO?>(totalIndex, init = {
            return@Array SampleItemDTO()
        })
        dynamicLayoutTemplate.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.outline_box,
            requireContext().theme
        )

        binding.contentLayout.addView(dynamicLayoutTemplate)

        Handler(Looper.getMainLooper()).postDelayed({
            dynamicLayoutTemplate.setTemplateValue(itemList)

        }, 2000)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getRoundedDrawable(iLeftColor: Int, iRightColor: Int): Drawable? {
        val iRadius = 7f
        val fArrLeftOuterRadius =
            floatArrayOf(iRadius, iRadius, iRadius, iRadius, iRadius, iRadius, iRadius, iRadius)
        val fArrRightOuterRadius = floatArrayOf(0f, 0f, iRadius, iRadius, iRadius, iRadius, 0f, 0f)
        val clTopRoundRectangle = RoundRectShape(fArrLeftOuterRadius, null, null)
        val clLeftShape = ShapeDrawable(clTopRoundRectangle)
        clLeftShape.paint.color = iLeftColor
        val clLeftroundrectangle = RoundRectShape(fArrRightOuterRadius, null, null)
        val clRightShape = ShapeDrawable(clLeftroundrectangle)
        clRightShape.paint.color = iRightColor
        val drawarray = arrayOf<Drawable>(clLeftShape, clRightShape)
        val clLayerDrawable = LayerDrawable(drawarray)
        clLayerDrawable.setLayerInset(0, 0, 0, 25, 16)
        clLayerDrawable.setLayerInset(1, 30, 0, 0, 16)
        return clLayerDrawable
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val s = numberList[position]
        totalItems = s.toInt()
        var value: Array<String> = Array(
            totalItems
        ) {
            return@Array "$it"
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}