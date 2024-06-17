package rees46.demo_android.features.recommendationBlock

// TODO: replace by real data
internal object ModelBuilder {

    fun getShortCardProducts() : List<ShortCardProduct> {
        val cardProducts = ArrayList<ShortCardProduct>()

        for (i in 0..10) {
            cardProducts.add(ShortCardProduct(i))
        }

        return cardProducts
    }
}