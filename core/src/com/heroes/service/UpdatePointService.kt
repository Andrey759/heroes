package com.heroes.service

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.heroes.component.RowColComponent
import com.heroes.component.point.*
import com.heroes.screen.BattleScreen.Companion.SPACE_BOT
import com.heroes.screen.BattleScreen.Companion.SPACE_LEFT
import com.heroes.screen.BattleScreen.Companion.SPACE_RIGHT
import com.heroes.screen.BattleScreen.Companion.SPACE_TOP
import kotlin.math.roundToInt

class UpdatePointService(var rowNum: Int, var colNum: Int) : IteratingSystem(Family.all(
    RowColComponent::class.java,
    CBotLeftPoint::class.java,
    CBotPoint::class.java,
    CBotRightPoint::class.java,
    CTopLeftPoint::class.java,
    CTopPoint::class.java,
    CTopRightPoint::class.java
).get()) {
    //private val H_KOEFF = 0.7F
    private val H_KOEFF = 0.866F
    private val TOP_BOT_KOEFF = 1 / 3F

    init {
        setProcessing(false)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val rowCol = RowColComponent.MAPPER.get(entity)
        val row = rowCol.row
        val col = rowCol.col
        val botLeft = CBotLeftPoint.MAPPER.get(entity)
        val bot = CBotPoint.MAPPER.get(entity)
        val botRight = CBotRightPoint.MAPPER.get(entity)
        val topLeft = CTopLeftPoint.MAPPER.get(entity)
        val top = CTopPoint.MAPPER.get(entity)
        val topRight = CTopRightPoint.MAPPER.get(entity)
        val startsFromLeft = row % 2 == 0

        val screenSize = calculateScreenSize()
        //val screenWidth = Gdx.graphics.width - SPACE_LEFT - SPACE_RIGHT
        //val screenHeight = screenWidth / colNum * rowNum * 0.965F / 1.4F
        val stepX = screenSize.width / (colNum + 0.5F)
        val stepY = screenSize.height / rowNum
        val rowDX = if (startsFromLeft) 0F else stepX / 2F

        bot.y = screenSize.dy + row * stepY

        botLeft.x = screenSize.dx + rowDX + col * stepX
        botLeft.y = bot.y + stepY * TOP_BOT_KOEFF
        topLeft.x = botLeft.x
        topLeft.y = bot.y + stepY
        botRight.x = botLeft.x + stepX
        botRight.y = botLeft.y
        topRight.x = botLeft.x + stepX
        topRight.y = bot.y + stepY

        top.x = topLeft.x + stepX / 2F
        top.y = topLeft.y + stepY * TOP_BOT_KOEFF
        bot.x = botLeft.x + stepX / 2F

        round(botLeft)
        round(topLeft)
        round(botRight)
        round(topRight)
    }

    private fun round(pointC: CAbstractPoint) {
        pointC.x = pointC.x.roundToInt().toFloat()
        pointC.y = pointC.y.roundToInt().toFloat()
    }

    private fun calculateScreenSize(): ScreenSize {
        val w = 1
        val h = 1F / (colNum + 0.5F) * (H_KOEFF * rowNum + TOP_BOT_KOEFF)
        val wth = w / h
        val screenWidth = Gdx.graphics.width - SPACE_LEFT - SPACE_RIGHT
        val screenHeight = Gdx.graphics.height - SPACE_TOP - SPACE_BOT
        // Полностью занимаем по высоте
        if (screenWidth / screenHeight > wth) {
            val dx = (screenWidth - screenHeight * wth) / 2F + SPACE_LEFT
            return ScreenSize(screenHeight * wth, screenHeight, dx, SPACE_BOT)
        } else {    // Полностью занимаем по ширине
            val dy = (screenHeight - screenWidth / wth) / 2F + SPACE_BOT
            return ScreenSize(screenWidth, screenWidth / wth, SPACE_LEFT, dy)
        }
    }

    private class ScreenSize(val width: Float, val height: Float, val dx: Float, val dy: Float) {
    }
}