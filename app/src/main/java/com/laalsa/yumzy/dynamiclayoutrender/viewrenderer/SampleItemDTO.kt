package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer

import com.laalsa.app.gericrecyclersample.recyclerview.template.IItemDTO

class SampleItemDTO : IItemDTO {

    override var tag: String = "tag"
    override var controlType: Byte = -1
    override var viewType: Byte = -1
    override var value: String = "some value"
    override var key: Int = -1
    override var text: String = "Food Food Food"
    
}