package com.example.k2026_04_01_met_tour_start

import android.util.Log
import com.example.k2026_04_01_met_tour_start.models.SearchStrings

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SearchStringsTest {
    lateinit var searchStrings: SearchStrings

    init {
        searchStrings = SearchStrings()
    }

    @Before
    fun startClean() {
        searchStrings.cleanAndDelete()
    }

    @Test
    fun emptySearch() {
        assertFalse(searchStrings.isInSearchList("FindMe"))
    }

    @Test
    fun insertAndSearchOneItem() {

        val searchTerm: String= "Americana"

        searchStrings.addSearchTerm(searchTerm)

        for (s in SearchStrings.searchStringList) {
            println(s)
            //Log.i("PGB", "in Search String list: $s")
        }
        assertTrue(searchStrings.isInSearchList(searchTerm))
    }

    @Test
    fun setGetCurrentItem() {

        val searchTerm: String= "Americana"
        SearchStrings.currentSearchString = searchTerm
        assertTrue(SearchStrings.currentSearchString == searchTerm)
    }
}