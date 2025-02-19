package com.zlsp.ppsphb.main

import android.app.Application
import com.zlsp.ppsphb.data.utils.YandexAdsUtils
import dagger.hilt.android.HiltAndroidApp
import org.orbitmvi.orbit.compose.BuildConfig
import ru.ok.tracer.CoreTracerConfiguration
import ru.ok.tracer.HasTracerConfiguration
import ru.ok.tracer.TracerConfiguration
import ru.ok.tracer.crash.report.CrashFreeConfiguration
import ru.ok.tracer.crash.report.CrashReportConfiguration
import ru.ok.tracer.disk.usage.DiskUsageConfiguration
import ru.ok.tracer.heap.dumps.HeapDumpConfiguration
import ru.ok.tracer.profiler.sampling.SamplingProfilerConfiguration
import ru.ok.tracer.profiler.systrace.SystraceProfilerConfiguration
import timber.log.Timber

@HiltAndroidApp
class App : Application(), HasTracerConfiguration {
    override fun onCreate() {
        super.onCreate()
        YandexAdsUtils.initYandex(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override val tracerConfiguration: List<TracerConfiguration>
        get() = listOf(
            CoreTracerConfiguration.build{} ,
            CrashReportConfiguration.build {},
            CrashFreeConfiguration.build {},
            HeapDumpConfiguration.build {},
            DiskUsageConfiguration.build {},
            SystraceProfilerConfiguration.build {},
            SamplingProfilerConfiguration.build {},
        )
}