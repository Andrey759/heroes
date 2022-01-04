package com.heroes.factory

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.heroes.component.CellComponent
import com.heroes.component.LineComponent
import com.heroes.component.AnimationComponent
import com.heroes.dto.BattleDto
import com.heroes.util.Animation

class GridFactory {
    private val spaceLeft = 60F
    private val spaceBottom = 100F
    private val spaceTop = 20F
    private val k = 0.965F

    fun create(battle: BattleDto): ArrayList<Entity> {
        val entities = ArrayList<Entity>()
        var botOne = (Gdx.graphics.width - spaceLeft * 2F) / battle.colnum
        var botLeft = Vector2(spaceLeft, spaceBottom)
        var topOne = botOne * k
        var topLeft = Vector2((Gdx.graphics.width - topOne * battle.colnum) / 2F, botLeft.y + botOne / 1.4F)
        for (x in 0 until battle.colnum) {
            val botLeftCurr = Vector2(botLeft.x + botOne * x, botLeft.y)
            val botRightCurr = Vector2(botLeftCurr.x + botOne, botLeftCurr.y)
            entities.add(Entity().add(LineComponent(x, 0, "bot", botLeftCurr, botRightCurr, Color.GRAY)))
        }
        for (y in 0 until battle.rownum) {
            for (x in 0 until battle.colnum) {
                val botLeftCurr = Vector2(botLeft.x + botOne * x, botLeft.y)
                val topLeftCurr = Vector2(topLeft.x + topOne * x, topLeft.y)
                val botRightCurr = Vector2(botLeftCurr.x + botOne, botLeftCurr.y)
                val topRightCurr = Vector2(topLeftCurr.x + topOne, topLeftCurr.y)
                val left = LineComponent(x, y, "left", botLeftCurr, topLeftCurr, Color.GRAY)
                val top = LineComponent(x, y, "top", topLeftCurr, topRightCurr, Color.GRAY)
                val center = Vector2(botLeftCurr.x + botOne / 2F, botLeftCurr.y + botOne / 5F / 1.4F)
                entities.add(Entity().add(left))
                entities.add(Entity().add(top))
                if (x == 1 && y == 1) {
                    entities.add(Entity().add(CellComponent(x, y, center, left, top)).add(Animation.ARCHER.createComponent()))
                //} else if (x == 2 && y == 1) {
                    //entities.add(Entity().add(CellComponent(x, y, center, left, top)).add(Animation.ARROW.createComponent2()))
                } else {
                    entities.add(Entity().add(CellComponent(x, y, center, left, top)))
                }
            }
            val botRightCurr = Vector2(Math.round(botLeft.x + botOne * battle.colnum).toFloat(), Math.round(botLeft.y).toFloat())
            val topRightCurr = Vector2(Math.round(topLeft.x + topOne * battle.colnum).toFloat(), Math.round(topLeft.y).toFloat())
            entities.add(Entity().add(LineComponent(battle.colnum, y, "right", topRightCurr, botRightCurr, Color.GRAY)))
            botOne = topOne
            botLeft = topLeft
            topOne = botOne * k
            topLeft = Vector2((Gdx.graphics.width - topOne * battle.colnum) / 2F, botLeft.y + botOne / 1.4F)
        }
        return entities
    }

    /*private fun createLeftBottom(x: Int, y: Int, colNum: Int, rowNum: Int): Vector2 {
        val k = 0.965.pow(y).toFloat()
        val bottomBottom = Gdx.graphics.width - spaceX * 2F
        val bottomCurrent = bottomBottom * k
        val bottomOne = bottomCurrent / colNum
        val spaceXCurrent = (Gdx.graphics.width - bottomCurrent) / 2F
        var spaceYCurrent = spaceY
        for (yy in 0 until y) {
            spaceYCurrent += 0.965.pow(yy).toFloat()
        }
        return Vector2(bottomOne * x + spaceXCurrent, spaceYCurrent)
    }*/

    /*private fun createTop(x: Int, y: Int, maxX: Int, maxY: Int): LineComponent {
        val topLeft = Vector2(Gdx.graphics.width * 0.9F / maxX * x - 20F, 140F)
        val topRight = Vector2()
        return LineComponent(topLeft, topRight, Color.GRAY)
    }*/
}