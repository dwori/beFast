package at.fh.swengb.beFast.drops.recyclerview.description

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.drops.DropsFragment.Companion.EXTRA_DROP_ID
import at.fh.swengb.beFast.drops.recyclerview.description.dao.DescriptionNote
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository
import kotlinx.android.synthetic.main.activity_description_note.*

class DescriptionNoteActivity : AppCompatActivity() {
    private val viewModel: DescriptionNoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_note)

        val dropID = intent.getStringExtra(EXTRA_DROP_ID)

        val drop_name = DropsRepository.dropById(dropID!!)?.name

        if (dropID != null) {
            dropName.text = DropsRepository.dropById(dropID)?.name ?: getString(R.string.name_error)
        }

        val noteFromDb = DropsRepository.findSameID(applicationContext, dropID.toString())

        personalNote.setText(noteFromDb?.text)

        //view model implementation
        viewModel.note.observe(this){
            personalNote_textView.text = it?.text
        }

        viewModel.findLessonNoteById(dropID.toString())



        save_note.setOnClickListener() {
            val noteObj = DescriptionNote(dropID.toString(), drop_name.toString(),personalNote.text.toString())
            DropsRepository.addDescriptionNote(applicationContext, noteObj)
            Toast.makeText(this, "Note saved.", Toast.LENGTH_LONG).show()

            /*val noteFromDb = DropsRepository.findSameID(applicationContext, dropID.toString())
            personalNote.setText(noteFromDb?.text)*/

        }
        // back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}