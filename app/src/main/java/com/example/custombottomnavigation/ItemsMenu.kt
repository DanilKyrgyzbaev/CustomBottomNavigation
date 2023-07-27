package com.example.custombottomnavigation

sealed class ItemsMenu(
    val icon: Int?,
    val title: String,
    val ruta: String
){
    object Home: ItemsMenu(R.drawable.baseline_home_24, "Home", "Home")
    object Pets: ItemsMenu(R.drawable.baseline_pets_24, "Pets", "Pents")
    object Stars: ItemsMenu(null, "Stars", "Stars")
    object Books: ItemsMenu(R.drawable.baseline_library_books_24, "Books", "Books")
    object Profile: ItemsMenu(R.drawable.baseline_person_24, "Profile", "Profile")


}