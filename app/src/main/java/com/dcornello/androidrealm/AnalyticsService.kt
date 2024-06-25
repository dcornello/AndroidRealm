package com.dcornello.androidrealm

import android.util.Log
import javax.inject.Inject

interface AnalyticsService {
    fun trackHello()
}

class AnalyticsServiceImpl @Inject constructor(val analyticsDependency: AnalyticsDependency): AnalyticsService {
    override fun trackHello() {
        Log.d("ANALYTICS_PRINTING_SERVICE", "AnalyticsImpl.trackHello called, analyticsDependency : ${analyticsDependency.value}")
    }
}

//external lib, don't touch it
class AnalyticsDependency {
    val value = "I am AnalyticsDependency"
}