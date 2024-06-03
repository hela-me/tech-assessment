package com.westpac.assesment.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.westpac.assesment.model.CreditCard

@Composable
fun CreditCardItem(creditCard: CreditCard) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = creditCard.creditCardType ?: "", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = creditCard.creditCardNumber ?: "", fontSize = 16.sp, color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = creditCard.creditCardExpiryDate ?: "", fontSize = 14.sp, color = Color.DarkGray
            )
        }
    }
}

@Composable
fun CreditCardList(creditCards: List<CreditCard>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)
    ) {
        items(creditCards) { creditCard ->
            CreditCardItem(creditCard = creditCard)
        }
    }
}