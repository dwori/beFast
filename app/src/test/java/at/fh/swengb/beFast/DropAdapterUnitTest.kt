package at.fh.swengb.beFast


import at.fh.swengb.beFast.drops.recyclerview.DropViewHolder
import at.fh.swengb.beFast.drops.recyclerview.DropsAdapter
import at.fh.swengb.beFast.models.drops.Drops
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DropAdapterUnitTest {
    val drops = (1..15).map {
        Drops(
                "drop ${it}",
                "Testing",
                "Unit Testing",
                "18.01.2021",
                "too expensive anyways",
                "https://www.google.com/url?sa=i&url=https",
                "https://www.google.com/url?sa=i&url=https",
                "https://www.google.com/url?sa=i&url=https",
                1
        )
    }
    @Test
    fun itemCount_isCorrect() {
        /** 1. test the itemCount() for an empty list
        * 2. test the itemCount() for a non-empty list */
        val adapter = DropsAdapter({print(it)})
        assertEquals(0,adapter.itemCount)
        adapter.dropList = drops
        assertEquals(15,adapter.itemCount)
    }
    @Test
    fun binding_isCorrect() {
        val adapter = DropsAdapter({print(it)})
        adapter.dropList = drops
        val mockHolder = Mockito.mock(DropViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder,9)
        verify(mockHolder,times(1)).bindItem(drops[9])
    }
}