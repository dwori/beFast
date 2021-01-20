package at.fh.swengb.beFast.drops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.drops.DropsFragment.Companion.EXTRA_DROP_ID
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.activity_description_note.*

class DescriptionNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_note)

        val dropID = intent.getStringExtra(EXTRA_DROP_ID)

        val drop_name = DropsRepository.dropById(dropID!!)?.name

        if (dropID != null) {
            dropName.text = DropsRepository.dropById(dropID)?.name ?: getString(R.string.name_error)
        }

        val noteFromDb = DropsRepository.findSameID(applicationContext, dropID.toString())
        //show stored personal note
        personalNote.setText(noteFromDb?.text)


        //save personal note in the database
        save_note.setOnClickListener() {
            val noteObj = DescriptionNote(dropID.toString(), drop_name.toString(),personalNote.text.toString())
            DropsRepository.addDescriptionNote(applicationContext, noteObj)
            Toast.makeText(this, "Note saved.", Toast.LENGTH_LONG).show()

            val noteFromDb = DropsRepository.findSameID(applicationContext, dropID.toString())
            personalNote.setText(noteFromDb?.text)

        }
    }
}