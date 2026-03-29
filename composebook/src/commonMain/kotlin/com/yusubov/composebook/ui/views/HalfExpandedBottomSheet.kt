package com.yusubov.composebook.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.yusubov.composebook.ui.foundation.theme.ComposeBookTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HalfExpandableBottomSheet(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    BoxWithConstraints {
        val halfHeightDp = with(density) { (constraints.maxHeight * 0.5f).toDp() }
        val maxHeightDp = with(density) { (constraints.maxHeight * 0.9f).toDp() }

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            sheetMaxWidth = Dp.Unspecified,
            containerColor = ComposeBookTheme.colors.surface,
            contentColor = ComposeBookTheme.colors.text,
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    color = ComposeBookTheme.colors.textSecondary
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = halfHeightDp, max = maxHeightDp * 0.9f)
            ) {
                content()
            }
        }
    }
}