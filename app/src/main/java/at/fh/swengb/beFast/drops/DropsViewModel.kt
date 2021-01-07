package at.fh.swengb.beFast.drops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.fh.swengb.beFast.R

class DropsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Drops Fragment"
    }
    val text: LiveData<String> = _text
}