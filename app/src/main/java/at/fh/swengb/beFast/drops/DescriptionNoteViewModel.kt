package at.fh.swengb.beFast.drops


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository

class DescriptionNoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteId :MutableLiveData<String> = MutableLiveData()

    val note: LiveData<DescriptionNote?> = Transformations.switchMap(noteId) {
        DropsRepository.findSameIDByLiveData(getApplication(), it)
    }

    val noteText = Transformations.map(note) {
        it?.text
    }

    fun findLessonNoteById(str: String) {
        noteId.value = str
    }



}