package br.senai.sp.jandira.lazycolumnroom

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lazycolumnroom.components.ProdutCard
import br.senai.sp.jandira.lazycolumnroom.dao.repository.ProductRepository
import br.senai.sp.jandira.lazycolumnroom.model.Product
import br.senai.sp.jandira.lazycolumnroom.ui.theme.LazyColumnRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnRoomTheme {
                // A surface container using the 'background' color from the theme
                // componente pai
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

// função que faz a composição da aplicação
@Composable
fun Greeting(name: String) {

    var nameState by remember {
        mutableStateOf("")
    }

    var priceState by remember {
        mutableStateOf("")
    }

    var productsState by remember {
        mutableStateOf(listOf<Product>())
    }

    val context = LocalContext.current
    val productRepository = ProductRepository(LocalContext.current)
    productsState = productRepository.getProductsList()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Listas com Jetpack Compose")

        OutlinedTextField(
            value = nameState,
            onValueChange = {nameState = it},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = priceState,
            onValueChange = {priceState = it},
            modifier = Modifier.fillMaxWidth()
        )
        
        Button(
            onClick = {
                val p = Product(
                    productName = nameState,
                    productPrice = priceState.toDouble()
                )
                val newId = productRepository.save(p)
                productsState = productRepository.getProductsList()
                Toast.makeText(context, "$newId", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }

        /*
        * formada por itens ao contrario da RecyclerView que era formada por holders
        * estrutura de uma Column
        * possui scroll automático
        * é possível criar uma lista dentro de outra lista
        */
        LazyColumn(modifier =  Modifier.padding(16.dp)) {
            /*
            * não é necessário utilizar o 'for' pois os componentes são reciclados e isso consome memória
            * define a quantidade de itens através de um número inteiro
            * para interagir com uma lista é necessário importar
            */
            items(productsState) {
                ProdutCard(product = it)
            }
        }
    }
}

// função que faz o preview da aplicação
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LazyColumnRoomTheme {
        Greeting("Android")
    }
}

/*
* LazyColumn
* model (package)
* Product (data class)
* items
* dao.repository (packaje)
* ProductRepository (Kotlin class)
* components (package)
* PoductCard (file)
*/