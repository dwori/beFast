package at.fh.swengb.beFast.ui.drops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DropsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is drops Fragment"
    }
    val text: LiveData<String> = _text
}