package com.andromeda.araserver.util

import org.tensorflow.Graph
import org.tensorflow.SavedModelBundle
import org.tensorflow.TensorFlow

class Bert {
    var graph:Graph? = null
    var hasRun = false
    init {
        println(TensorFlow.version())
        val modelBundle = SavedModelBundle.load("") //TODO: put model stream here. that may be hard so figure it out
        graph = modelBundle.graph()
        hasRun = true
    }
    fun getNames(text:String){
        println(graph!!.operation(text))
    }
}