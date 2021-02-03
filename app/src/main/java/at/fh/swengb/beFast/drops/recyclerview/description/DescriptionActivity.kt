package at.fh.swengb.beFast.drops.recyclerview.description

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_description.*
import java.text.SimpleDateFormat
import java.util.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.drops.DropsFragment.Companion.EXTRA_DROP_ID
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository
import at.fh.swengb.beFast.models.drops.Drops
import com.bumptech.glide.Glide

/**
 *
 * The DescriptionActivity gets opened from the DropsFragment whenever you click on one drop. It provides
 * useful information and gives the chance to personalize your favorite drops.
 *
 */

class DescriptionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        /**
         * We get the corresponding data by referencing with the id of one drop
         * and fill them into the TextViews and ImageView(s).
         */

        var drop: Drops?
        val dropId = intent.getStringExtra(EXTRA_DROP_ID)

        if (dropId != null) {
            drop = DropsRepository.dropById(dropId)
            if (drop != null) {
                description_brand.text = drop.brand
                description_name.text = drop.name
                description_price.text = drop.price
                description_description.text = getString(drop.descriptionTextId)
                // set image
                Glide.with(this)
                    .load(drop.imageUrl)
                    .into(this.description_imageView)
            }
        }


        /**
         *
         * In order to pass the data of on drop in third party apps,
         * we define values for them.
         *
         */
        val brand = dropId?.let { DropsRepository.dropById(it)?.brand } ?: getString(R.string.brand_error)
        val name = dropId?.let { DropsRepository.dropById(it)?.name } ?: getString(R.string.name_error)
        val datetime = dropId?.let { DropsRepository.dropById(it)?.datetime } ?: getString(R.string.date_error)
        val homepageUrl = dropId?.let { DropsRepository.dropById(it)?.homepageUrl } ?: getString(R.string.hompageURL_error)
        val stockxUrl = dropId?.let { DropsRepository.dropById(it)?.stockxUrl } ?: "no stockx URL found"


        /**
         *
         * The share button passes on the brand, name and homepage of one drop, when sharing it.
         *
         */
        description_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "$brand $name Drop by beFast: $homepageUrl")
                putExtra(Intent.EXTRA_TITLE, "$brand $name")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        /**
         *
         *
         * First, a time pattern is defined and then the datetime (release date of one drop) and timezone get parsed with SimpleDateFormat.
         * When clicking the description_reminder button, androids calendar app opens and the datetime is inserted in the reminder field as a type of notification.
         * The reminder time is set to be 10 minutes before a drops releases.
         * Title and Description are passed as well.
         *
         *
         */
        description_reminder.setOnClickListener {
            val sdf = SimpleDateFormat("dd.MM.yyyy z HH:mm", Locale.ENGLISH)
            val formattedDate = sdf.parse(datetime)
            sdf.timeZone = TimeZone.getDefault()

            val calendarIntent = Intent(Intent.ACTION_EDIT)
            calendarIntent.type = "vnd.android.cursor.item/event"
            calendarIntent.putExtra("beginTime", formattedDate!!.time - 600000)
            calendarIntent.putExtra("endTime", formattedDate.time)
            calendarIntent.putExtra("title", "$brand $name Drop")
            calendarIntent.putExtra("description","Drop Reminder beFast App")
            startActivity(calendarIntent)
        }

        /**
         * The description_homepage brings you to the website of the brand.
         */
        description_homepage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(homepageUrl)
            startActivity(intent)
        }

        /**
         * The description_note opens the DescriptionNoteActivity and passes the id.
         */
        description_note.setOnClickListener {
            val noteIntent = Intent(this, DescriptionNoteActivity::class.java)
            noteIntent.putExtra(EXTRA_DROP_ID, dropId)
            startActivity(noteIntent)
        }

        /**
         * The description_stockX opens the StockX-Url of the drop.
         */
        description_stockX.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(stockxUrl)
            startActivity(intent)
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