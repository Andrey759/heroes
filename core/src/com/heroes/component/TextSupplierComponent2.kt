package com.heroes.component

import com.badlogic.ashley.core.ComponentMapper

class TextSupplierComponent2(x: Float, y: Float) :
    TextSupplierComponent({ "" }, x, y) {
    companion object { val MAPPER = ComponentMapper.getFor(TextSupplierComponent2::class.java) }

    override fun getText(): String {
        return "${x.toInt()}.${y.toInt()}"
    }
}