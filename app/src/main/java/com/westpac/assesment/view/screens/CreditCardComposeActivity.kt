package com.westpac.assesment.view.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.westpac.assesment.util.Constants.TAG_SCREEN_CREDIT_CARDS
import com.westpac.assesment.view.component.CreditCardList
import com.westpac.assesment.view.component.CustomLoadingIndicator
import com.westpac.assesment.view.theme.AssessmentTheme
import com.westpac.assesment.view.viewmodel.CreditCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditCardComposeActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AssessmentTheme {
                val navController = rememberNavController()
                Scaffold(topBar = {
                    Surface(shadowElevation = 4.dp) {
                        TopAppBar(title = {
                            Text(text = "Credit Cards")
                        })
                    }
                }) { padding ->
                    NavHost(
                        navController = navController, startDestination = TAG_SCREEN_CREDIT_CARDS, modifier = Modifier.padding(padding)
                    ) {
                        composable(TAG_SCREEN_CREDIT_CARDS) { CreditCardScreen() }
                    }
                }
            }
        }
    }

    @Composable
    fun CreditCardScreen() {
        val viewModel = hiltViewModel<CreditCardViewModel>()
        val creditCards by viewModel.creditCards.collectAsState()
        val isLoading by viewModel.isLoadingData.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                CustomLoadingIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                CreditCardList(creditCards = creditCards)
            }
        }
    }
}