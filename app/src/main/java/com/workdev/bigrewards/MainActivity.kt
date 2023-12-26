package com.workdev.bigrewards

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.google.ar.core.Frame
import com.workdev.bigrewards.databinding.ActivityMainBinding
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.node.ModelNode
import java.nio.file.WatchEvent

class MainActivity : AppCompatActivity() {
    private lateinit var model: ArModelNode
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)























        binding.button.setOnClickListener {

           place()
        }
        model = ArModelNode().apply {
            loadModelGlbAsync(glbFileLocation = "model/sofa_single.glb")

           {
                binding.viewr.planeRenderer.isVisible = true
            }
            onAnchorChanged = {
                binding.button.isGone

            }

        }

        binding.viewr.addChild(model)
    }


    private fun place() {

        model.anchor()
        binding.viewr.isVisible = false


    }


}