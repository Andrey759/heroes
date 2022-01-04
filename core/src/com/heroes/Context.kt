package com.heroes

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.heroes.factory.GridFactory

class Context(val batch: Batch = SpriteBatch(), val camera: OrthographicCamera = OrthographicCamera()) {
    val gridFactory = GridFactory()
}