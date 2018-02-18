package palmut.ru.loadersample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<SampleData> {
    private val TAG = "MainActivity"

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<SampleData> {
        Log.d(TAG, "onCreateLoader: id=$id args=$args")
        resultText.text = "onCreateLoader"
        return SampleLoader(this)
    }

    override fun onLoadFinished(loader: Loader<SampleData>?, data: SampleData?) {
        Log.d(TAG, "onLoadFinished: data=$data")
        resultText.text = data?.toString()
    }

    override fun onLoaderReset(loader: Loader<SampleData>?) {
        Log.d(TAG, "onLoaderReset: loader=$loader")
        resultText.text = "onLoaderReset"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: savedInstanceState=$savedInstanceState")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        restartLoader.setOnClickListener {
            supportLoaderManager.restartLoader(SampleLoader.LOADER_ID, Bundle(), this)
        }

        sendIntent.setOnClickListener {
            sendBroadcast(Intent(SampleLoader.RELOAD_ACTION))
        }

        childActivity.setOnClickListener {
            startActivity(Intent(this, ChildActivity::class.java))
        }
        supportLoaderManager.initLoader(SampleLoader.LOADER_ID, Bundle(), this)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d(TAG, "onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}
