@file:Suppress("DEPRECATION")

package at.fh.swengb.beFast.news

import android.os.AsyncTask
import android.util.Log

/**
 * This class SleepyAsyncTask is used to implement a basic background task
 * it is used so that the thread is waiting for the api call to fetch all the data
 */
class SleepyAsyncTask : AsyncTask<Unit, Unit, Unit>() {
    /**
     * Runs on the main thread
     * Sets up the task
     */
    override fun onPreExecute() {
        super.onPreExecute()
        Log.e("SleepingAsyncTask", "Going to sleep")
        Log.i("SleepingAsyncTask", Thread.currentThread().name)

    }

    /**
     * runs on a background thread
     * All the work to happen in the background
     */
    override fun doInBackground(vararg params: Unit?) {
        Thread.sleep(5000)
        Log.i("SleepingAsyncTask", Thread.currentThread().name)
    }

    /**
     * runs on main thread when work done
     * Process results and publish to the UI
     */
    override fun onPostExecute(result: Unit?) {
        super.onPostExecute(result)
        Log.e("SleepingAsyncTask", "Done sleeping")
        Log.i("SleepingAsyncTask", Thread.currentThread().name)
    }
}