package com.laalsa.app.gericrecyclersample.recyclerview.template

import androidx.recyclerview.widget.RecyclerView
import com.laalsa.app.gericrecyclersample.recyclerview.data.IKeyValue

interface IItemDTO : IKeyValue {
    var controlType: Byte
    var viewType: Byte
    var tag: String
}
