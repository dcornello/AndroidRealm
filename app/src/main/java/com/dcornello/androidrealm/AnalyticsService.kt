package com.dcornello.androidrealm

import android.util.Log
import javax.inject.Inject

interface AnalyticsService {
    fun trackHello()
}

class AnalyticsServiceImpl @Inject constructor(): AnalyticsService {
    override fun trackHello() {
        Log.d("ANALYTICS_PRINTING_SERVICE", "AnalyticsImpl.trackHello called")
    }
}

