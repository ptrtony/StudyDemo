package com.zhaofan.studaydemo.openGL

import android.opengl.GLSurfaceView
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zhaofan.studaydemo.R

class OpenGLActivity : AppCompatActivity() {
    private var supportsEs2:Boolean?=null
    private var glSurfaceView:GLSurfaceView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_gl)

    }


}
