package com.example.sprint_2_kotlin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.sprint_2_kotlin.model.data.NewsItem
import com.example.sprint_2_kotlin.viewmodel.NewsFeedViewModel

@Composable
fun NewsCard(item: NewsItem, viewModel: NewsFeedViewModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Image
            Image(
                painter = rememberAsyncImagePainter(item.image_url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Category and reliability
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = viewModel.getCategoryLabel(item.category_id),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                ReliabilityChip(item.average_reliability_score)
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Short description
            Text(
                text = item.short_description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Author + days since + total ratings
            Text(
                text = "${item.author_type} at ${item.author_institution}",
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "${item.days_since} days ago • ${item.total_ratings} ratings",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
fun ReliabilityChip(score: Double) {
    val color = when {
        score >= 90 -> MaterialTheme.colorScheme.tertiary
        score >= 70 -> MaterialTheme.colorScheme.secondary
        else -> MaterialTheme.colorScheme.error
    }
    AssistChip(
        onClick = {},
        label = {
            Text("${score.toInt()}%")
        },
        colors = AssistChipDefaults.assistChipColors(containerColor = color)
    )
}
