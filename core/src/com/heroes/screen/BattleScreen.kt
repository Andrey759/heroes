package com.heroes.screen

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.heroes.Context
import com.heroes.component.BackgroundComponent
import com.heroes.component.SizeComponent
import com.heroes.component.TextSupplierComponent
import com.heroes.component.TextSupplierComponent2
import com.heroes.dto.BattleDto
import com.heroes.system.BackgroundSystem
import com.heroes.system.LineSystem
import com.heroes.system.Text2System
import com.heroes.system.TextSystem
import com.heroes.util.Image

class BattleScreen(private val context: Context): Screen, Engine() {

    init {
        super.addSystem(BackgroundSystem(context))
        //super.addSystem(LineSystem(context))
        //super.addSystem(AnimationSystem(context))
        super.addSystem(TextSystem(context))
        super.addSystem(Text2System(context))

        super.addEntity(Entity().add(BackgroundComponent(Image.LAND_TAIGA_3.texture())).add(SizeComponent(2048F, 2048F)))

        context.gridFactory.create(BattleDto()).forEach { super.addEntity(it) }

        super.addEntity(Entity().add(TextSupplierComponent({ "${Gdx.graphics.width}x${Gdx.graphics.height}" }, 50F, 50F)))
        super.addEntity(Entity().add(TextSupplierComponent2(Gdx.graphics.width - 100F, Gdx.graphics.height - 100F)))
    }


    override fun show() {
    }

    override fun render(delta: Float) {
        super.update(Gdx.graphics.deltaTime)
    }

    override fun resize(width: Int, height: Int) {
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