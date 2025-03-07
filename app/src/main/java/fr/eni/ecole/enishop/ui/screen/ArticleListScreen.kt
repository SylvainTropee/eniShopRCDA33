package fr.eni.ecole.enishop.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.eni.ecole.enishop.vm.ArticleListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import fr.eni.ecole.enishop.bo.Article
import fr.eni.ecole.enishop.ui.common.EniShopTopBar
import java.util.Date

@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory)
) {

    val articles by viewModel.articles.collectAsState()
    val categories by viewModel.categories.collectAsState()

    var selectedCategory by rememberSaveable {
        mutableStateOf("")
    }

    val selectedArticles = if (selectedCategory != "") {
        articles.filter {
            it.category == selectedCategory
        }
    } else {
        articles
    }

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        Column(modifier = Modifier.padding(it)) {
            CategoryFilterChip(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategoryChange = {
                    selectedCategory = it
                }
            )
            ArticleList(articles = selectedArticles)
        }
    }
}

@Composable
fun ArticleList(articles: List<Article>) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(articles) {
            ArticleItem(article = it)
        }

    }


}


@SuppressLint("DefaultLocale")
@Composable
fun ArticleItem(article: Article, modifier: Modifier = Modifier) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .padding(8.dp)
            )
            Text(text = article.name)
            Text(text = "${String.format("%.2f", article.price)} €")
        }
    }
}


@Composable
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String = "",
    onCategoryChange: (String) -> Unit = {}
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = category == selectedCategory,
                onClick = {
                    if (selectedCategory == category) {
                        onCategoryChange("")
                    } else {
                        onCategoryChange(category)
                    }
                },
                label = { Text(text = category) },
                leadingIcon = if (selectedCategory == category) {
                    {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Category Checked"
                        )
                    }
                } else {
                    null
                }
            )
        }
    }


}