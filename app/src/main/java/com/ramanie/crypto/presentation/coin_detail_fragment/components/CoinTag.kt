package com.ramanie.crypto.presentation.coin_detail_fragment.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramanie.crypto.common.Tag

@Composable
fun CoinTag(tag: Tag) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .border(BorderStroke(width = 1.5.dp,
                color = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(20))
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = tag.name,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
            )
    }
}