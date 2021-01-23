package at.fh.swengb.beFast.drops.recyclerview

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Resources
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.drops.DescriptionNote
import at.fh.swengb.beFast.drops.dao.DescriptionNoteDB
import at.fh.swengb.beFast.models.drops.Drops

//val id0: String = Context.getString(R.string.id0)

    //val id0: String = getSystem().getString(R.string.id0)

object DropsRepository {

    //val id0: String = Resources.getSystem().getString(R.string.id0)


    /*private var context1: Context? = null

    fun getContext(con: Context) {
         context1 = con
     }

    fun setApplication(application: Application) {
        context1 = application.applicationContext
    }*/


    val drops: List<Drops> = listOf(
            Drops("0","Nike", "Air Force 1 Rayguns", "05.02.2021 +0100 09:00", "120€", "https://stockx.imgix.net/images/Nike-Air-Force-1-Low-Raygun.png?fit=fill&bg=FFFFFFhttps://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2021%2F01%2Fadidas-zx-5000-vieux-lyon-jacquard-release-details-05.jpg?q=75&w=800&cbr=1&fit=max","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/nike-air-force-1-low-raygun", R.string.id0),
            Drops("1","Supreme","Cross Box Logo Tee Red","06.02.2021 +0100 12:00","60€","https://stockx.imgix.net/images/Supreme-Cross-Box-Logo-Tee-Red.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1608222060&w=1000","https://www.supremenewyork.com","https://stockx.com/de-de/supreme-cross-box-logo-tee-red", R.string.id1),
            Drops("2","Puma","XT2+ Hanon","06.02.2021 +0100 12:00","80€","https://stockx.imgix.net/Puma-XT2-Hanon-Adventurer-Pack.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000","https://eu.puma.com/at/de/home","https://stockx.com/de-de/puma-xt2-hanon-adventurer-pack",R.string.id2),
            Drops("3","Adidas", "ZX 5000 Vieux Lyon", "07.02.2020 +0100 11:00", "120€", "https://stockx.imgix.net/images/adidas-ZX-5000-Vieux-Lyon.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609866973&w=1000","https://www.adidas.at","https://stockx.com/de-de/adidas-zx-5000-vieux-lyon",R.string.id3),
            Drops("4","Fear of God","Sail Black","07.02.2020 +0100 12:00","380€","https://stockx-360.imgix.net/Nike-Air-Fear-Of-God-1-Sail-Black/Images/Nike-Air-Fear-Of-God-1-Sail-Black/Lv2/img01.jpg?auto=format,compress&q=90&updated_at=1608522448&w=1000","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/nike-air-fear-of-god-1-sail-black",R.string.id4),
            Drops("5","New Balance","670 Year of the Rat","08.02.2020 +0100 09:00","150€","https://stockx.imgix.net/New-Balance-670-Year-of-the-Rat-2020.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1603481985&w=1000","https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwih9O3-o4zuAhUY7u0KHT8yCMUYABARGgJkZw&ohost=www.google.at&cid=CAESQOD2NDDJ9mnh5Wj20ooFLlW8TyGEpWuzyRAUGq852aIX7fH6Z4blfSj3RPuEyabhJb6ja-Hod5jCb7_GCR37Kxg&sig=AOD64_3LsCX32axTa8BBwJyN6IZ7QQzlig&q&adurl&ved=2ahUKEwjyhuT-o4zuAhWfaRUIHTtVASUQ0Qx6BAgGEAE","https://stockx.com/de-de/new-balance-670-year-of-the-rat-2020",R.string.id5),
            Drops("6","Nike","Nike Lebron 18 Kylian Mbappe","08.02.2020 +0100 09:00","185€","https://stockx.imgix.net/images/Nike-Lebron-18-Kylian-Mbappe.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609866953&w=1000","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/nike-lebron-18-kylian-mbappe",R.string.id6),
            Drops("7","Nike","Air Force 1 Low Orange Skeleton","08.02.2020 +0100 09:00","89,99€","https://stockx.imgix.net/images/Nike-Air-Force-1-Low-Orange-Skeleton-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&dpr=2&trim=color&updated_at=1609438989","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/nike-air-force-1-low-orange-skeleton",R.string.id7),
            Drops("8","Nike","Dunk High Ambush Active","10.02.2020 +0100 09:00","180€","https://stockx.imgix.net/images/Nike-Dunk-High-Ambush-Active-Fuchsia.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609406830&w=1000","https://www.nike.com/de/","https://stockx.com/de-de/nike-dunk-high-ambush-active-fuchsia?utm_source=af&utm_medium=imp&utm_campaign=2089136&impactSiteId=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5QT6lWRRXU0&clickid=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5QT6lWRRXU0&utm_term=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5QT6lWRRXU0&utm_content=_530344&irgwc=1",R.string.id8),
            Drops("9","Nike","Air Jordan 1 High OG Gold", "10.02.2020 +0100 09:00","160€","https://stockx.imgix.net/images/Air-Jordan-1-Retro-High-White-Black-Volt-University-Gold.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1605645198&w=1000","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/air-jordan-1-retro-high-white-black-volt-university-gold?utm_source=af&utm_medium=imp&utm_campaign=2089136&impactSiteId=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5hRilWRRXU0&clickid=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5hRilWRRXU0&utm_term=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5hRilWRRXU0&utm_content=_530344&irgwc=1",R.string.id9),
            Drops("10","Nike","Air Max 97 Sail","12.02.2020 +0100 09:00","180€","https://stockx.imgix.net/images/Nike-Air-Max-97-Undefeated-UCLA.png?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&trim=color&updated_at=1609455487&w=1000","https://www.nike.com/de/launch?s=upcoming","https://stockx.com/de-de/nike-air-max-97-undefeated-ucla?utm_source=af&utm_medium=imp&utm_campaign=2089136&impactSiteId=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5mzalWRRXU0&clickid=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5mzalWRRXU0&utm_term=0wTzeIXuvxyOWzVwUx0Mo3EAUkEy5mzalWRRXU0&utm_content=_530344&irgwc=1",R.string.id10)
    )

    fun dropById(id: String): Drops? {
        return drops.find { it.id == id }
    }
    fun addDescriptionNote(context: Context, descriptionNote: DescriptionNote) {
        val applicationContext = context.applicationContext
        val db = DescriptionNoteDB.getDatabase(applicationContext)
        db.descriptionNoteDao.insert(descriptionNote)
    }
    fun findSameID(context: Context, id: String): DescriptionNote {
        val applicationContext = context.applicationContext
        val db = DescriptionNoteDB.getDatabase(applicationContext)
        return db.descriptionNoteDao.findDropBySameID(id)
    }
}