package com.example.cupcakeapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcakeapp.R

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )

    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.price
    )

    val newOrder = stringResource(R.string.new_cupcake_order)

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = stringResource(R.string.quantity),
                fontWeight = FontWeight.Bold
            )
            Text(text = numberOfCupcakes)

            Divider(thickness = dimensionResource(R.dimen.thickness_divider))

            Text(
                text = stringResource(R.string.flavor),
                fontWeight = FontWeight.Bold
            )
            Text(text = orderUiState.flavor)

            Divider(thickness = dimensionResource(R.dimen.thickness_divider))

            Text(
                text = stringResource(R.string.pickup_date),
                fontWeight = FontWeight.Bold
            )
            Text(text = orderUiState.date)

            Divider(thickness = dimensionResource(R.dimen.thickness_divider))

            Text(
                text = stringResource(R.string.total, orderUiState.price),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            OutlinedButton(
                onClick = onCancelButtonClicked,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                onClick = { onSendButtonClicked(newOrder, orderSummary) },
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.send))
            }
        }
    }
}

@Preview
@Composable
fun OrderSummaryPreview() {
    CupcakeAppTheme {
        OrderSummaryScreen(
            orderUiState = OrderUiState(
                quantity = 6,
                flavor = "Vanilla",
                date = "Mon Jan 1",
                price = "$15.00"
            ),
            onCancelButtonClicked = {},
            onSendButtonClicked = { _, _ -> }
        )
    }
}