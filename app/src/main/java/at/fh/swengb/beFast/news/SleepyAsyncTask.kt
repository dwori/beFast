@file:Suppress("DEPRECATION")

package at.fh.swengb.beFast.news

import android.os.AsyncTask
import android.util.Log

class SleepyAsyncTask : AsyncTask<Unit, Unit, Unit>() {

    override fun onPreExecute() {
        super.onPreExecute()
        Log.e("SleepingAsyncTask", "Going to sleep")
        Log.i("SleepingAsyncTask", Thread.currentThread().name)

    }

    override fun doInBackground(vararg params: Unit?) {
        Thread.sleep(5000)
        Log.i("SleepingAsyncTask", Thread.currentThread().name)
    }

    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.e("SleepingAsyncTask", "Done sleeping")
        Log.i("SleepingAsyncTask", Thread.currentThread().name)
    }
}