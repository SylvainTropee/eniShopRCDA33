package fr.eni.ecole.enishop.dao.memory

import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.dao.ArticleDAO

class ArticleDAOMemoryImpl : ArticleDAO {

    val articles : MutableList<Article> = mutableListOf(
        Article(
            id = 1,
            name = "Stylo-Bille",
            price = 12.35,
            category = "Fournitures",
            urlImage = "www.image.com",
            description = "Il est beau !"
        )
    )

    override fun findById(id: Long): Article {
       return articles.first {
           it.id == id
       }
    }

    override fun insert(article: Article): Long {
        article.id = articles.size.toLong() + 1
        articles.add(article)
        return article.id
    }
}