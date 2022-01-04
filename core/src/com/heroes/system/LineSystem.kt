package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.heroes.Context
import com.heroes.component.LineComponent

class LineSystem(private val context: Context) : IteratingSystem(Family.all(LineComponent::class.java).get()) {
    private val renderer: ShapeRenderer = ShapeRenderer()
    //private val camera: OrthographicCamera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    init {
        //camera.position.set(Gdx.graphics.width / 2F, Gdx.graphics.height / 2F, 0F)
        //camera.update()
        //renderer.projectionMatrix = camera.combined
        renderer.color = Color(0x7f7f7f00)
    }

    override fun update(deltaTime: Float) {
        //Gdx.gl.glEnable(GL20.GL_BLEND)
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        renderer.begin(ShapeRenderer.ShapeType.Filled)

        super.update(deltaTime)

        renderer.end()
        //Gdx.gl.glDisable(GL20.GL_BLEND)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val line = LineComponent.MAPPER.get(entity)

        renderer.rectLine(
            Math.round(line.from.x).toFloat(),
            Math.round(line.from.y).toFloat(),
            Math.round(line.to.x).toFloat(),
            Math.round(line.to.y).toFloat(),
            3F,
            Color(0x7f7f7f7f),
            Color(0x7f7f7f7f)
        )
    }
}