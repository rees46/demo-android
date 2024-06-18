package rees46.demo_android.features.product

// TODO: replace by real data
internal object ModelBuilder {

    fun getProducts() : List<Product> {
        val products = ArrayList<Product>()

        for (i in 0..10) {
            val product = Product(i)
            products.add(product)
        }

        return products
    }
}