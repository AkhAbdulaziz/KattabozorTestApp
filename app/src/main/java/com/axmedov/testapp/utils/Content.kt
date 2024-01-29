package com.axmedov.testapp.utils

const val sampleImageUrl =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Smartphone_Use.jpg/220px-Smartphone_Use.jpg"

const val sampleImageUrl2 =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/SamsungGalaxyS20plus5G128G2020SMG9860TaiwanFrontReady20200910.jpg/170px-SamsungGalaxyS20plus5G128G2020SMG9860TaiwanFrontReady20200910.jpg"

val imagesList = listOf<String>(
    sampleImageUrl,
    sampleImageUrl2,
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/IPhone_11_Green.svg/199px-IPhone_11_Green.svg.png",
    "https://kattabozor.s3.eu-central-1.amazonaws.com/ri/41c82aab0c8a74cad829dcb7bb150c85e020ac84dfbce821f2a473dba44245c4_FR8ah7_640l.jpg",
    sampleImageUrl,
    sampleImageUrl2,
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/IPhone_11_Green.svg/199px-IPhone_11_Green.svg.png",
    "https://kattabozor.s3.eu-central-1.amazonaws.com/ri/41c82aab0c8a74cad829dcb7bb150c85e020ac84dfbce821f2a473dba44245c4_FR8ah7_640l.jpg",
    sampleImageUrl,
    sampleImageUrl2,
    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/IPhone_11_Green.svg/199px-IPhone_11_Green.svg.png",
    "https://kattabozor.s3.eu-central-1.amazonaws.com/ri/41c82aab0c8a74cad829dcb7bb150c85e020ac84dfbce821f2a473dba44245c4_FR8ah7_640l.jpg"
)

/*
@Composable
fun CreditCardScreen(viewModel: CreditCardViewModel) {
    val creditCards by viewModel.creditCards.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchCreditCards()
    }

    Column {
        if (creditCards.isEmpty()) {
            // Show loading indicator or placeholder
            Text(text = "Loading...")
        } else {
            // Display the list of credit cards
            LazyColumn {
                items(creditCards) { creditCard ->
                    Text(text = creditCard.bank)
                    Text(text = creditCard.number)
                    Text(text = creditCard.type)
                    Divider() // Add a divider between items
                }
            }
        }
    }
}
*/