package com.heroes.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.esotericsoftware.spine.*
import com.heroes.Context
import com.heroes.component.*
import com.heroes.util.AnimationType

class AnimationSystem(private val context: Context) : IteratingSystem(Family.all(AnimationComponent::class.java).get()) {

    private val batch = PolygonSpriteBatch()
    //private val camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val renderer = SkeletonRenderer()
    private var total = 0F;
    private var done = false;

    init {
        //camera.position.set(Gdx.graphics.width / 2F, Gdx.graphics.height / 2F, 0F)
        //camera.update()
        renderer.setPremultipliedAlpha(true)
    }

    override fun update(deltaTime: Float) {
        total += deltaTime;

        //camera.update()
        batch.begin()
        //batch.projectionMatrix = camera.combined

        super.update(deltaTime)

        batch.end()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent = AnimationComponent.MAPPER.get(entity)
        val skeleton = animationComponent.skeleton
        val state = animationComponent.state
        /*if (total > 0F && !done && ) {
            //done = true
            //total = 0F
            animationComponent.playAndStop(AnimationType.SHOOT)
        }*/

        state.update(deltaTime)
        state.apply(skeleton)
        skeleton.updateWorldTransform()

        renderer.draw(batch, skeleton)
    }
}