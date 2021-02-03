package at.fh.swengb.beFast


import at.fh.swengb.beFast.models.tweets.Entities
import at.fh.swengb.beFast.models.tweets.Media
import at.fh.swengb.beFast.models.tweets.TweetsItem
import at.fh.swengb.beFast.models.tweets.Url
import at.fh.swengb.beFast.news.recyclerview.TweetAdapter
import at.fh.swengb.beFast.news.recyclerview.TweetViewHolder
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsAdapterUnitTest {
    val tweets = (1..30).map {
        TweetsItem(
            "Nike Jordan",
            "14.01.2021",
            Entities(listOf<Url>(), listOf<Media>())
        )
    }
    @Test
    fun itemCount_isCorrect() {
        /** 1. test the itemCount() for an empty list
        * 2. test the itemCount() for a non-empty list */
        val adapter = TweetAdapter({print(it)})
        assertEquals(0,adapter.itemCount)
        adapter.tweetList = tweets
        assertEquals(30,adapter.itemCount)
    }
    @Test
    fun binding_isCorrect() {
        val adapter = TweetAdapter({print(it)})
        adapter.tweetList = tweets
        val mockHolder = Mockito.mock(TweetViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder,9)
        verify(mockHolder,times(1)).bindItem(tweets[9])
    }
}

