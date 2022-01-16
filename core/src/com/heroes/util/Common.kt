package com.heroes.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.esotericsoftware.spine.SkeletonRenderer
import com.heroes.util.RenderType.*

class Common {
    companion object {
        private lateinit var camera: OrthographicCamera
        lateinit var batch: SpriteBatch
        lateinit var animationBatch: PolygonSpriteBatch
        lateinit var lineRender: ShapeRenderer
        lateinit var skeletonRender: SkeletonRenderer

        fun create() {
            batch = SpriteBatch()
            animationBatch = PolygonSpriteBatch()
            lineRender = ShapeRenderer()
            skeletonRender = SkeletonRenderer()
            skeletonRender.premultipliedAlpha = true

            camera = OrthographicCamera()
            resize()
        }

        fun resize() {
            camera.viewportWidth = Gdx.graphics.width.toFloat()
            camera.viewportHeight = Gdx.graphics.height.toFloat()
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0F)
            camera.update()
            batch.projectionMatrix = camera.combined
            animationBatch.projectionMatrix = camera.combined
            lineRender.projectionMatrix = camera.combined
        }

        fun prepareBatch() {
            endAllExceptOne(BATCH)
            if (!batch.isDrawing) {
                batch.begin()
            }
        }

        fun prepareAnimationBatch() {
            endAllExceptOne(ANIMATION_BATCH)
            if (!animationBatch.isDrawing) {
                animationBatch.begin()
            }
        }

        fun prepareLineRender() {
            endAllExceptOne(LINE_RENDER)
            if (!lineRender.isDrawing) {
                lineRender.begin(ShapeRenderer.ShapeType.Line)
            }
        }

        fun endAll() {
            endAllExceptOne(null)
        }

        private fun endAllExceptOne(renderType: RenderType?) {
            if (renderType != BATCH && batch.isDrawing) {
                batch.end()
            }
            if (renderType != ANIMATION_BATCH && animationBatch.isDrawing) {
                animationBatch.end()
            }
            if (renderType != LINE_RENDER && lineRender.isDrawing) {
                lineRender.end()
            }
        }



        fun clear() {
            Gdx.gl.glClearColor(0.2F, 0.4F, 0.2F, 1F)
            Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        }

        fun blendOn() {
            Gdx.gl.glEnable(GL20.GL_BLEND)
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        }

        fun blendOff() {
            Gdx.gl.glDisable(GL20.GL_BLEND)
        }
    }
}