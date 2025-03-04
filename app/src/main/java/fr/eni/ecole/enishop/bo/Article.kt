package fr.eni.ecole.enishop.bo

import java.util.Date

data class Article(
    var id : Long = 0,
    var name : String,
    var description : String,
    var price : Double,
    var urlImage : String,
    var category : String,
    var date : Date = Date(),
)
