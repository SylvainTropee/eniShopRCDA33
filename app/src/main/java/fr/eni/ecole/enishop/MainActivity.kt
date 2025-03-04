package fr.eni.ecole.enishop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.repository.ArticleRepository
import fr.eni.ecole.enishop.ui.theme.EniShopTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var article = ArticleRepository.getArticle(1)
        ArticleRepository.addArticle(
            Article(
                category = "Truc",
                urlImage = "mahcin",
                description = "",
                name = "ARticle 2",
                price = 23.0
            )
        )
        Log.i(TAG, article.toString())
        article = ArticleRepository.getArticle(2)
        Log.i(TAG, article.toString())
    }
}
