package com.heroes

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.heroes.screen.BattleScreen
import com.heroes.util.Common

class HGame : ApplicationListener {
    private lateinit var screen: BattleScreen

    override fun create() {
        Common.create()
        screen = BattleScreen()
    }

    override fun resize(width: Int, height: Int) {
        Common.resize()
        screen.resize(width, height)
    }

    override fun render() {
        Common.clear()
        screen.update(Gdx.graphics.deltaTime)
        Common.endAll()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        screen.dispose()
    }
}