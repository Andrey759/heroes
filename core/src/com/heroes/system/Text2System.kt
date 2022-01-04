package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.heroes.Context
import com.heroes.component.TextSupplierComponent
import com.heroes.component.TextSupplierComponent2

class Text2System(private val context: Context) : IteratingSystem(Family.all(TextSupplierComponent2::class.java).get()) {
    //private val batch: SpriteBatch = SpriteBatch()
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    init {
        //camera.position.set(Gdx.graphics.width / 2F, Gdx.graphics.height / 2F, 0F)
        //camera.update()
    }

    override fun update(deltaTime: Float) {
        //camera.update()
        //batch.begin()
        //batch.projectionMatrix = camera.combined

        super.update(deltaTime)

        //batch.end()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val component = TextSupplierComponent2.MAPPER.get(entity)
        val font = component.font
        val text = component.getText()
        val x = component.x
        val y = component.y

        font.draw(context.batch, text, x, y)
    }
}