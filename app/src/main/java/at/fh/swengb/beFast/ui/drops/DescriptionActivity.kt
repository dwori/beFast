package at.fh.swengb.beFast.ui.drops

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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



        // share intent
        description_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, brand + " " + name)
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



    }
}