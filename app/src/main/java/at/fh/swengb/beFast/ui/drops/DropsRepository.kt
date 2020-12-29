package at.fh.swengb.beFast.ui.drops

import at.fh.swengb.beFast.models.Drops

object DropsRepository {
    val drops: List<Drops>

    init {
        drops = listOf(
                Drops("0","Nike", "Air Force 1", "31.12.2020", "09:00", "280$", "https://sizeer.at/media/cache/gallery/rc/pc2kfrx8/nike-air-force-1-gs-junior-sneaker-weiss-314192-117_3.jpg"),
                Drops("1","Adidas", "Air Force 1", "31.12.2020", "10:00", "250$", "https://stockx.imgix.net/images/adidas-Yeezy-Boost-350-V2-Glow-Product.jpg?fit=fill&bg=FFFFFF&w=700&h=500&auto=format,compress&q=90&dpr=2&trim=color&updated_at=1606320244"),
                Drops("2","Crocs", "Air Force 1", "31.12.2020", "11:00", "250$", "https://images.crocs.com/is/image/Crocs/205516_0DA_ALT140?&fmt=jpeg&qlt=85,1&op_sharpen=0&resMode=sharp2&op_usm=1,1,6,0&iccEmbed=0&printRes=72&wid=440&hei=365")


        )


    }

    fun dropsList(): List<Drops> {
        return drops
    }

    fun dropById(id: String): Drops? {
        return drops.find { it.id == id}
    }
}





