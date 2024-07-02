package rees46.demo_android.features.main.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.personalizatio.SDK
import com.personalizatio.api.listeners.OnCategoriesListener
import com.personalizatio.entities.categories.categories.CategoriesEntity
import com.personalizatio.entities.categories.category.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import rees46.demo_android.features.product.Product
import rees46.demo_android.features.product.ProductUtils

class CategoryViewModel(
    private val sdk: SDK
): ViewModel() {

    private val _productsFlow: MutableSharedFlow<ArrayList<Product>> = MutableSharedFlow()
    val productsFlow: Flow<List<Product>> = _productsFlow
    private val products = arrayListOf<Product>()

    init {
        sdk.categoriesManager.getCategories(object : OnCategoriesListener {
            override fun onGetCategories(categoriesEntity: CategoriesEntity) {
                for (categoryEntity in categoriesEntity) {
                    updateProducts(categoryEntity.id)
                }
            }
        })
    }

    private fun updateProducts(categoryId: String) {
        sdk.categoriesManager.getCategory(categoryId, object : OnCategoriesListener {
            override fun onGetCategory(categoryEntity: CategoryEntity) {
                for (product in categoryEntity.products) {
                    products.add(ProductUtils.createProduct(product))
                }

                updateProducts()
            }
        })
    }

    private fun updateProducts() {
        viewModelScope.launch {
            _productsFlow.emit(products)
        }
    }
}
