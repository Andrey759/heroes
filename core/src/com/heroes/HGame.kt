package com.heroes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.heroes.screen.BattleScreen

class HGame : ApplicationListener {
    private lateinit var context: Context
    private lateinit var screen: BattleScreen

    override fun create() {
        context = Context()
        screen = BattleScreen(context)
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun render() {
        Gdx.gl.glClearColor(0.2F, 0.4F, 0.2F, 1F)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        context.batch.begin()

        screen.update(Gdx.graphics.deltaTime)

        context.batch.end()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        screen.dispose()
    }
}