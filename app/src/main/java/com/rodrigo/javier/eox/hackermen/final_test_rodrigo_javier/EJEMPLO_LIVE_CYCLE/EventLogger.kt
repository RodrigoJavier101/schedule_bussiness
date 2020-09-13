package com.rodrigo.javier.eox.hackermen.final_test_rodrigo_javier.EJEMPLO_LIVE_CYCLE

import android.util.Log
import androidx.lifecycle.Lifecycle

class EventLogger {
    companion object {
        private const val TAG = "EventLogs"
    }
    fun eventLog(event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> Log.d(TAG, "OnCreate")
            Lifecycle.Event.ON_START -> Log.d(TAG, "OnStart")
            Lifecycle.Event.ON_RESUME -> Log.d(TAG, "OnResume")
            Lifecycle.Event.ON_PAUSE -> Log.i(TAG, "onPause")
            Lifecycle.Event.ON_STOP -> Log.i(TAG, "onStop")
            Lifecycle.Event.ON_DESTROY -> Log.i(TAG, "onDestroy")
            /**
             * ON_ANY no es un ciclo de vida, lo dejaremos por si ocurre algún
            evento no cubierto
             * con las opciones anteriores.
             */
            Lifecycle.Event.ON_ANY -> Log.i(TAG, "onAny")
        }
    }
}