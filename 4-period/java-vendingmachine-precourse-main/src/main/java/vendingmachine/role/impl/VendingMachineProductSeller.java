package vendingmachine.role.impl;

import java.util.Map;
import vendingmachine.domain.money.Coin;
import vendingmachine.domain.money.Money;
import vendingmachine.domain.product.Product;
import vendingmachine.dto.PurchaseResult;
import vendingmachine.exception.SoldOutException;
import vendingmachine.exception.domain.NotEnoughMoneyException;
import vendingmachine.role.ChangeProvider;
import vendingmachine.role.MoneyManager;
import vendingmachine.role.ProductManager;
import vendingmachine.role.ProductSeller;

/*
- [ ] 상품을 판매하고 결과를 반환한다.
    - 상품 품절 시 (throw Exception)
    - 잔액 부족 시 (throw Exception)
 */
public class VendingMachineProductSeller implements ProductSeller {
    private final ProductManager productManager;
    private final MoneyManager moneyManager;
    private final ChangeProvider changeProvider;

    public VendingMachineProductSeller(final ProductManager productManager, final MoneyManager moneyManager,
                                       final ChangeProvider changeProvider) {
        this.productManager = productManager;
        this.moneyManager = moneyManager;
        this.changeProvider = changeProvider;
    }

    @Override
    public PurchaseResult sell(final String productName, final Money insertedMoney) {
        Product product = productManager.findProduct(productName);
        validatePurchase(product, insertedMoney);

        Product updatedProduct = product.decreaseQuantity();
        productManager.updateProduct(updatedProduct);

        Money change = insertedMoney.minus(product.getPrice());
        Map<Coin, Integer> coins = changeProvider.provideChange(change);

        Money actualChange = Money.of(calculateChangeAmount(coins));
        Money reaminMoney = moneyManager.subtract(actualChange);

        return new PurchaseResult(updatedProduct, coins, reaminMoney);
    }

    private void validatePurchase(Product product, Money insertedMoney) {
        if (!product.hasStock()) {
            throw new SoldOutException();
        }
        if (insertedMoney.isLessThan(product.getPrice())) {
            throw new NotEnoughMoneyException();
        }
    }

    private int calculateChangeAmount(Map<Coin, Integer> coins) {
        return coins.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getAmount() * entry.getValue())
                .sum();
    }

}
