package com.example.apbooks

data class MyDataItem(
    val amount: Int,
    val appleIAP: AppleIAP,
    val copiesTaken: Int,
    val coverImage: String,
    val id: String,
    val isDraft: Boolean,
    val isUpcoming: Boolean,
    val language: String,
    val originalAmount: Int,
    val pages: Int,
    val paperBookURL: String,
    val shortDescription: String,
    val showPaperBookInApp: Boolean,
    val subtitle: String,
    val title: String,
    val type: String
)