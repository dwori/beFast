package at.fh.swengb.beFast.ui.drops

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.ui.drops.DropsFragment.Companion.EXTRA_DROP_ID
import kotlinx.android.synthetic.main.activity_description.*
import java.text.SimpleDateFormat
import java.util.*


class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val dropId = intent.getStringExtra(EXTRA_DROP_ID)

        if (dropId != null) {
            description_brand.text = DropsRepository.dropById(dropId)?.brand ?: "no brand found"
            description_name.text = DropsRepository.dropById(dropId)?.name ?: "no name found"
            description_price.text = DropsRepository.dropById(dropId)?.price ?: "no price found"

        }


        val brand = dropId?.let { DropsRepository.dropById(it)?.brand } ?: "no brand found"
        val name = dropId?.let { DropsRepository.dropById(it)?.name } ?: "no name found"
        val datetime = dropId?.let { DropsRepository.dropById(it)?.datetime } ?: "no date found"
        val homepageUrl = dropId?.let { DropsRepository.dropById(it)?.homepageUrl } ?: "no homepageUrl found"



        // share intent
        description_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, brand + " " + name + " " + "Drop" + " " + "by" + " " + "beFast")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


        //reminder intent
        description_reminder.setOnClickListener {
            val sdf = SimpleDateFormat("dd.MM.yyyy z HH:mm", Locale.ENGLISH)
            val formattedDate = sdf.parse(datetime)
            sdf.timeZone = TimeZone.getDefault()

            val calendarIntent = Intent(Intent.ACTION_EDIT)
            calendarIntent.type = "vnd.android.cursor.item/event"
            calendarIntent.putExtra("beginTime", formattedDate.time - 600000)
            calendarIntent.putExtra("endTime", formattedDate.time)
            calendarIntent.putExtra("title",brand + " " + name + " " + "Drop")
            calendarIntent.putExtra("description","Drop Reminder beFast App")
            startActivity(calendarIntent)
        }

        description_homepage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(homepageUrl)
            startActivity(intent)
        }

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