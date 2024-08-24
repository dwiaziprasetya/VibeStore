package com.example.vibestore.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    imageId: String,
    productName: String,
    category: String,
    price: String,
    orderCount: Int,
    checkedValue: Boolean = false,
    onQuantityChange: (Int) -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(checkedValue) }
    var quantity by remember { mutableIntStateOf(orderCount) }
    val totalPrice = price.toDouble() * quantity

    LaunchedEffect(checkedValue) {
        checked = checkedValue
    }

    Row(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .height(120.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Box {
            RoundedCornerCheckbox(
                label = "",
                isChecked = checked,
                onValueChange = {
                    checked = it
                    onCheckedChange(it)
                },
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        AsyncImage(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterVertically),
            model = imageId,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = productName,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Text(
                text = "Category : $category",
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                maxLines = 1,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "$${"%.2f".format(totalPrice)}",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        ProductCounter(
            modifier = Modifier.align(Alignment.CenterVertically),
            orderCount = orderCount,
            onIncrement = {
                quantity += 1
                onQuantityChange(quantity)
                if (checked) onCheckedChange(checked)
            },
            onDecrement = {
                quantity -= 1
                onQuantityChange(quantity)
                if (quantity == 0) {
                    onQuantityChange(0)
                }
                if (checked) onCheckedChange(checked)
            }
        )
    }
}

@Composable
fun CartItemDummy(
    modifier: Modifier = Modifier,
    productName: String,
    category: String,
    price: String,
    orderCount: Int,
) {
    val totalPrice = price.toDouble() * orderCount

    Row(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .height(120.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        Box {
            RoundedCornerCheckbox(
                label = "",
                isChecked = false,
                onValueChange = {}
            )
        }
        Image(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(R.drawable.image_clothes),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = productName,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Text(
                text = "Category : $category",
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                maxLines = 1,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "$${"%.2f".format(totalPrice)}",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun RoundedCornerCheckbox(
    label: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    size: Float = 20f,
    checkedColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedColor: Color = MaterialTheme.colorScheme.background,
    onValueChange: (Boolean) -> Unit
) {
    val checkboxColor: Color by animateColorAsState(if (isChecked) checkedColor else uncheckedColor,
        label = ""
    )
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(
                    color = checkboxColor,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = if (isChecked) checkedColor else MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(duration)
                ),
                exit = fadeOut()
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = uncheckedColor
                )
            }
        }
        if (label != "") {
            androidx.compose.material.Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = label,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun CartItemMini(
    modifier: Modifier = Modifier,
    productName: String,
    imageId: String,
    price: String,
    orderCount: Int,
    totalOrder: Int,
    onDetailOrder: () -> Unit,
) {
    val totalPrice = price.toDouble() * orderCount

    Row(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .height(80.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .size(60.dp),
            model = imageId,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = productName,
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Row {
                Text(
                    text = "$orderCount x $${"%.2f".format(totalPrice)}",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )
                if (totalOrder > 1) {
                    Text(
                        text = "and ${totalOrder - 1} more",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { onDetailOrder() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
private fun CartItemPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        CartItemDummy(
            productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            price = "78",
            orderCount = 2,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CartItemMiniPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        CartItemMini(
            productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = "78",
            orderCount = 2,
            imageId = "",
            totalOrder = 3,
            onDetailOrder = {}
        )
    }
}