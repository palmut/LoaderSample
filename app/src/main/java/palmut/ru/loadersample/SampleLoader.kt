package palmut.ru.loadersample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.AsyncTaskLoader
import android.util.Log
import java.util.*
import java.util.concurrent.TimeUnit

class SampleLoader(context: Context) : AsyncTaskLoader<SampleData>(context) {
    val TAG = "SampleLoader"

    var cached: SampleData? = null

    override fun onStartLoading() {
        Log.d(TAG, "onStartLoading")

        registerObserver()
        if (takeContentChanged() || cached == null) {
            forceLoad()
        } else {
            deliverResult(cached)
        }
    }

    override fun onStopLoading() {
        Log.d(TAG, "onStopLoading")
        cancelLoad()
    }

    override fun onReset() {
        Log.d(TAG, "onReset")
        super.onReset()
        onStopLoading()
        cached = null
        unregisterObserver()
    }

    override fun deliverResult(data: SampleData?) {
        Log.d(TAG, "deliverResult: data=$data")
        if (!isReset) {
            cached = data
            if (isStarted) {
                super.deliverResult(data)
            }
        }
    }

    override fun loadInBackground(): SampleData {
        Log.d(TAG, "loadInBackground")
        Thread.sleep(TimeUnit.SECONDS.toMillis(3))
        return SampleDataWithString(Date(System.currentTimeMillis()).toString())
    }

    private fun registerObserver() {
        Log.d(TAG, "registerObserver")
        context.registerReceiver(intentReceiver, IntentFilter(RELOAD_ACTION))
    }

    private fun unregisterObserver() {
        Log.d(TAG, "unregisterObserver")
        context.unregisterReceiver(intentReceiver)
    }

    private val intentReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            onContentChanged()
        }
    }

    companion object {
        const val RELOAD_ACTION = "ACTION.reload"
        const val LOADER_ID = 1
    }
}