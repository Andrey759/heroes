package com.heroes.screen

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.heroes.component.BackgroundComponent
import com.heroes.component.SizeComponent
import com.heroes.factory.CellFactory
import com.heroes.service.UpdatePointService
import com.heroes.system.*
import com.heroes.util.Animation
import com.heroes.util.Image
import kotlin.math.sqrt

class BattleScreen: Screen, Engine() {
    companion object {
        val SPACE_LEFT = 60F
        val SPACE_RIGHT = 60F
        val SPACE_TOP = 100F
        val SPACE_BOT = 100F
    }

    init {
        super.addSystem(UpdatePointService(7, 8))

        super.addSystem(BackgroundSystem())
        super.addSystem(BorderSystem())
        super.addSystem(AnimationSystem())
        super.addSystem(TextSystem())
        super.addSystem(Text2System())

        super.addEntity(Entity().add(BackgroundComponent(Image.LAND_TAIGA_3.texture())).add(SizeComponent(2048F, 2048F)))
        CellFactory.getCellList(7, 8).forEach { super.addEntity(it) }
        //super.addEntity(Entity().add(TextSupplierComponent({ "${Gdx.graphics.width}x${Gdx.graphics.height}" }, 50F, 50F)))
        super.addEntity(Entity().add(Animation.ARCHER.createComponent()))

        super.getSystem(UpdatePointService::class.java).update(0F)
    }


    override fun show() {
    }

    override fun render(delta: Float) {
        super.update(Gdx.graphics.deltaTime)
    }

    override fun resize(width: Int, height: Int) {
        super.getSystem(UpdatePointService::class.java).update(0F)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        super.removeAllEntities()
    }
}