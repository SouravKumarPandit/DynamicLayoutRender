package com.laalsa.yumzy.dynamiclayoutrender

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.laalsa.yumzy.dynamiclayoutrender.databinding.FragmentFirstBinding
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.SampleItemDTO
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template.DynamicLayoutTemplate
import com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.template.IItemDTO


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null
    var numberList = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    //    var zeroNumberList = arrayOf("0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    var dimensionArray: IntArray = intArrayOf()
    private val binding get() = _binding!!
    private var totalItems: Int = 5
    private var layoutTotal: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialButton.setOnClickListener {
            binding.contentLayout.removeAllViews()

        }

        val aa = object :
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                numberList
            ) {

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                dropDownView.setPadding(55, 25, 55, 25)
                dropDownView.background = getRoundedDrawable(0x80444444.toInt(), Color.WHITE)
                return dropDownView
            }

        }
        binding.totalSpinner.adapter = aa
        binding.totalSpinner.onItemSelectedListener = this
        binding.totalSpinner.setPopupBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.totalSpinner.setPadding(25, 25, 55, 25)
        binding.totalSpinner.background = getRoundedDrawable(0x80444444.toInt(), Color.WHITE)

        binding.btnAddLayout.setOnClickListener {
            addLayoutToContainer()
        }
        binding.addSpinnerBtn.setOnClickListener {
            val childCount = binding.itemLayout.childCount
            val viewIndex = childCount + 1
            val array = Array(viewIndex) { return@Array 1 }
            dimensionArray = array.toIntArray()
            val spinnerContent = getSpinnerContent(viewIndex)
            binding.itemLayout.addView(spinnerContent)

        }
    }

    private fun addLayoutToContainer() {

        if (binding.itemLayout.childCount == 0) {
            Toast.makeText(
                requireContext(),
                "Add Spinner With Field to display",
                Toast.LENGTH_SHORT
            ).show()
            return
        }


        val dynamicLayoutTemplate =
            DynamicLayoutTemplate(
                context = requireContext(),
                totalIndex = totalItems,
                layoutDimension = dimensionArray
            )
        val itemList = Array<IItemDTO?>(totalItems, init = {
            return@Array SampleItemDTO()
        })
//        dynamicLayoutTemplate.inflateListItemView()
        /*dynamicLayoutTemplate.background = ResourcesCompat.getDrawable(
            resources,
            R.drawable.outline_box,
            requireContext().theme
        )*/
        val materialCardView = MaterialCardView(requireContext())
        materialCardView.setBackgroundColor(Color.WHITE)
        materialCardView.radius = 8f
        materialCardView.cardElevation = 8f
        materialCardView.setPadding(5, 5, 5, 5)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(15, 15, 15, 15)
        materialCardView.layoutParams = layoutParams

        materialCardView.addView(dynamicLayoutTemplate)


        binding.contentLayout.addView(materialCardView)

        Handler(Looper.getMainLooper()).postDelayed({
            dynamicLayoutTemplate.setTemplateValue(itemList)
        }, 3000)

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
        val leftRectangle = RoundRectShape(fArrRightOuterRadius, null, null)
        val clRightShape = ShapeDrawable(leftRectangle)
        clRightShape.paint.color = iRightColor

        val drawArray = arrayOf<Drawable>(clLeftShape, clRightShape)
        val clLayerDrawable = LayerDrawable(drawArray)
        clLayerDrawable.setLayerInset(0, 2, 8, 0, 8)
        clLayerDrawable.setLayerInset(1, 30, 10, 2, 10)
        return clLayerDrawable
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val s = numberList[position]
        totalItems = s.toInt()
        binding.itemLayout.removeAllViews()
    }

    private fun getSpinnerContent(viewIndex: Int): Spinner {
        val spinner = Spinner(requireContext())
        spinner.setPadding(25, 25, 55, 25)
        spinner.background = getRoundedDrawable(0x80444444.toInt(), Color.WHITE)
        spinner.setPopupBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val aa = object :
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                numberList
            ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val dropDownView = super.getDropDownView(position, convertView, parent)
                dropDownView.setPadding(35, 25, 55, 25)
                dropDownView.background = getRoundedDrawable(0x80444444.toInt(), Color.WHITE)
                return dropDownView
            }
        }

        spinner.adapter = aa
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val numberString = numberList[position]
                val numberValue = numberString.toInt()
                layoutTotal += numberValue
                dimensionArray[viewIndex - 1] = numberValue
                if (layoutTotal > totalItems) {
                    Toast.makeText(
                        requireContext(),
                        "Select Valid Layout Total",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        return spinner
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}