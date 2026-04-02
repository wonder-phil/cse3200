package com.example.k2026_04_01_met_tour_start.models

import android.util.Log

class SearchStrings {

    companion object {
        var currentSearchString: String = ""
        var searchStringList: MutableList<String> = mutableListOf<String>()
    }

    fun addSearchTerm(term: String) {
        //Log.i("PGB","Added term $term")
        if (term != "")
            searchStringList += term.lowercase()
    }

    fun isInSearchList(term: String) : Boolean {
        return term.lowercase() in searchStringList
    }

    fun cleanAndDelete() {
        currentSearchString = ""
        searchStringList.clear()
    }

}