package fr.eni.ecole.enishop.dao

import fr.eni.ecole.enishop.bo.Article

interface ArticleDAO {

    fun findById(id : Long) : Article

    fun insert(article: Article) : Long

    fun findAll() : List<Article>
}