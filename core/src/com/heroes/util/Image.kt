package com.heroes.util

import com.badlogic.gdx.graphics.Texture

enum class Image(private val path: String) {
    LAND_TAIGA_3("land/land_tajga3.jpg"),
    ;

    fun texture(): Texture {
        return Texture(path)
    }
}