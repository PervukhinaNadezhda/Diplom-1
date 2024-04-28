package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTests {

    private final Database database = new Database();
    private final IngredientType firstIngredientType = database.availableIngredients().get(0).type;
    private final String firstIngredientName = database.availableIngredients().get(0).name;
    private final float firstIngredientPrice = database.availableIngredients().get(0).price;

    @Test
    public void getNameReturnIngredientNameTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientName, ingredient.getName());
    }

    @Test
    public void getPriceReturnIngredientPriceTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientPrice, ingredient.getPrice(), 0f);
    }

    @Test
    public void getTypeReturnIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientType, ingredient.getType());
    }

}