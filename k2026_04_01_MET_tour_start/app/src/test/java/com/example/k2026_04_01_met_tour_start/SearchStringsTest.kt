package com.example.k2026_04_01_met_tour_start

import com.example.k2026_04_01_met_tour_start.models.SearchStrings
import org.junit.Test

import org.junit.Assert.*



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SearchStringsTest {
    @Test
    fun emtpySearch() {

        val searchStrings: SearchStrings = SearchStrings()
        assertFalse(searchStrings.isInSearchList("FindMe"))
    }

    @Test
    fun insertAndSearchOneItem() {

        val searchTerm: String= "Americana"

        val searchStrings: SearchStrings = SearchStrings()
        searchStrings.addSearchTerm(searchTerm)

        assertTrue(searchStrings.isInSearchList(searchTerm))
    }

    @Test
    fun setGetCurrentItem() {

        val searchTerm: String= "Americana"
        val searchStrings: SearchStrings = SearchStrings()
        SearchStrings.currentSearchString = searchTerm

        assertTrue(SearchStrings.currentSearchString == searchTerm)
    }
}