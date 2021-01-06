package at.fh.swengb.beFast.brands

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BrandsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is brands Fragment"
    }
    val text: LiveData<String> = _text
}