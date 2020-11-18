package com.laalsa.yumzy.dynamiclayoutrender.viewrenderer

object CLViewIdGenerator {
    private var INTERNAL_VIEW_ID = 50000
    @JvmStatic
    fun generateNewId(): Int {
        if (INTERNAL_VIEW_ID >= 65535) {
            INTERNAL_VIEW_ID = 50000
        } else {
            ++INTERNAL_VIEW_ID
        }
        return INTERNAL_VIEW_ID
    }
}