package com.heroes.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.esotericsoftware.spine.*
import com.heroes.component.AnimationComponent

enum class Animation(private val atlasPath: String, private val skeletonPath: String, private val skin: String) {
    ARCHER("archer/Archer_Cut.atlas.txt", "archer/Archer_Cut.json", "Archer_Normal"),
    ARROW("arrow/Archer_Cut.atlas.txt", "arrow/Archer_Cut.json", "default"),
    ;

    private var scale = 0.3F
    private var timeScale = 0.6F
    private val inaction1 = "inaction1"
    private val inaction2 = "inaction2"

    private lateinit var skeletonData: SkeletonData
    private lateinit var stateData: AnimationStateData

    init {
        load()
        if (atlasPath.startsWith("archer")) {
            AnimationType.values().forEach { anim1 ->
                AnimationType.values()
                    .filter {
                        anim1 != it
                    }
                    .forEach {
                            anim2 -> stateData.setMix(anim1.formal, anim2.formal, 0.2F)
                    }
            }
        }
    }

    fun load() {
        val atlas = TextureAtlas(Gdx.files.internal(atlasPath))
        val json = SkeletonJson(atlas)
        json.scale = scale
        skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonPath))
        stateData = AnimationStateData(skeletonData)
    }

    fun createComponent(): AnimationComponent {
        val skeleton = Skeleton(skeletonData)
        skeleton.setPosition(265F, 3 * 125F)
        skeleton.setSkin(skin)
        skeleton.rootBone.scaleX = -1F
        val state = AnimationState(stateData)
        state.timeScale = timeScale
        state.setAnimation(0, AnimationType.SHOOT.formal, true)
        state.addListener(object: AnimationState.AnimationStateListener {
            override fun start(entry: AnimationState.TrackEntry?) {
            }

            override fun interrupt(entry: AnimationState.TrackEntry?) {
            }

            override fun end(entry: AnimationState.TrackEntry?) {
            }

            override fun dispose(entry: AnimationState.TrackEntry?) {
            }

            override fun complete(entry: AnimationState.TrackEntry?) {
            }

            override fun event(entry: AnimationState.TrackEntry?, event: Event?) {
                println(entry.toString() + "" + event)
            }

        })
        return AnimationComponent(skeleton, state)
    }

    fun createComponent2(): AnimationComponent {
        val skeleton = Skeleton(skeletonData)
        skeleton.setPosition(265F + 50F, 125F + 205F)
        skeleton.setSkin(skin)
        val state = AnimationState(stateData)
        state.timeScale = timeScale
        state.setAnimation(0, "Arrow_Flyup", false)
        return AnimationComponent(skeleton, state)
    }
}