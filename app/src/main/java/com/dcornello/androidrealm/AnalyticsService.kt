package com.dcornello.androidrealm

import android.util.Log

interface AnalyticsService {
    fun trackHello()
}

class AnalyticsServiceImpl: AnalyticsService {
    override fun trackHello() {
        Log.d("ANALYTICS_PRINTING_SERVICE", "AnalyticsImpl.trackHello called")
    }
}

