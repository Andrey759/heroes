package com.heroes.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont

open class TextSupplierComponent(var textSupplier: () -> String, var x: Float, var y: Float): Component {
    companion object { val MAPPER = ComponentMapper.getFor(TextSupplierComponent::class.java) }

    var font: BitmapFont = BitmapFont(Gdx.files.internal("font/24_devroyun.fnt"))

    open fun getText(): String {
        return textSupplier.invoke()
    }
}