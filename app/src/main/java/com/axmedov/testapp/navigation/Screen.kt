package com.axmedov.testapp.navigation

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"

sealed class Screen(val route: String) {
     object Main : Screen(route = "main_screen")

     object Detail : Screen(route = "detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}") {
//        fun passId(id: Int): String {
//            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", id.toString())
//        }

        fun passIdAndName(
            id: Int,
            name: String
        ): String {
            return "detail_screen/$id/$name"
        }
    }
}
