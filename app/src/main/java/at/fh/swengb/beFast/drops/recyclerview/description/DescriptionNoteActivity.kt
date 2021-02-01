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
        /**
         * ViewModel implementation for having live data for DescriptionNote
         */
        viewModel.findLessonNoteById(dropID.toString())
        viewModel.note.observe(this){
            personalNote_textView.text = it?.text
            personalNote.setText(it?.text)
        }

        val dropname = dropID?.let { DropsRepository.dropById(it)?.name }
        dropName.text = dropname

        /**
         * saves the note and inserts it into the database
         */
        save_note.setOnClickListener() {
            val noteObj = DescriptionNote(dropID.toString(), dropname.toString(),personalNote.text.toString())
            DropsRepository.addDescriptionNote(applicationContext, noteObj)
            Toast.makeText(this, getString(R.string.note_saved), Toast.LENGTH_LONG).show()
            finish()
        }
        /**
         * this makes sure that the back button redirects to the previous fragment.
         */
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