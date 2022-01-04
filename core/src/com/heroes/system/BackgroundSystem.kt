package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.heroes.Context
import com.heroes.component.BackgroundComponent
import com.heroes.component.SizeComponent

class BackgroundSystem(private val context: Context) : IteratingSystem(Family.all(BackgroundComponent::class.java, SizeComponent::class.java).get()) {


    private val batch: SpriteBatch = SpriteBatch()
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    init {
        //camera.position.set(Gdx.graphics.width / 2F, Gdx.graphics.height / 2F, 0F)
        //camera.update()
    }

    override fun update(deltaTime: Float) {
        //camera.update()
        batch.begin()
        //batch.projectionMatrix = camera.combined

        super.update(deltaTime)

        batch.end()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val texture = BackgroundComponent.MAPPER.get(entity).texture
        val size = SizeComponent.MAPPER.get(entity)

        context.batch.draw(texture, 0F, 0F, size.width, size.height)
    }
}