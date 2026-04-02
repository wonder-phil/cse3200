package com.example.k2026_04_01_met_tour_start.models

import android.util.Log

class SearchStrings {

    companion object {
        var currentSearchString: String = ""
        private var searchStringList: MutableList<String> = mutableListOf<String>()
    }

    fun addSearchTerm(term: String) {
        Log.i("PGB","Added term $term")
        searchStringList += term
    }

    fun isInSearchList(term: String) : Boolean {
        if (term in searchStringList) {
            return true
        } else {
            return false
        }
    }

}