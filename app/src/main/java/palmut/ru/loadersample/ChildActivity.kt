package palmut.ru.loadersample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import kotlinx.android.synthetic.main.activity_child.*

class ChildActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<SampleData> {
    val TAG = "ChildActivity"

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<SampleData> {
        Log.d(TAG, "onCreateLoader: id=$id")
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
        setContentView(R.layout.activity_child)

        supportLoaderManager.initLoader(SampleLoader.LOADER_ID, Bundle(), this)
    }
}
