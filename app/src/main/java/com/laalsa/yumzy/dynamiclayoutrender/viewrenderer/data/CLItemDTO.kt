package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer.data

import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO

class CLItemDTO : IItemDTO //used to handle field names
{
    override var tag: String = ""
    override var controlType: Byte = -1
    override var viewType: Byte = -1
    override var value: String = ""
    override var key: Int = -1
    override var text: String = ""

}