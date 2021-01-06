package at.fh.swengb.beFast.drops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DropsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Follow brands to see drops"
    }
    val text: LiveData<String> = _text
}