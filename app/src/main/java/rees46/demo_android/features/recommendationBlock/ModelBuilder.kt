package rees46.demo_android.features.recommendationBlock

// TODO: replace by real data
internal object ModelBuilder {

    fun getCardProducts() : List<CardProduct> {
        val cardProducts = ArrayList<CardProduct>()

        for (i in 0..10) {
            cardProducts.add(CardProduct(i))
        }

        return cardProducts
    }
}