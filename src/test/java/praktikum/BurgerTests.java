package praktikum;

import helper.GenerateRandom;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    private final Database database = new Database();
    private final Bun firstBun = database.availableBuns().get(0);

    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(firstBun);
        Assert.assertEquals(firstBun.name, burger.bun.getName());
    }

    @Test
    public void addIngredientInvokedTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        Assert.assertTrue(burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        int index = burger.ingredients.indexOf(firstIngredient);

        burger.removeIngredient(index);

        Assert.assertFalse(burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        int indexFirstIngredient = burger.ingredients.indexOf(firstIngredient);
        int indexSecondIngredient = burger.ingredients.indexOf(secondIngredient);

        burger.moveIngredient(indexFirstIngredient, indexSecondIngredient);

        int newIndexFirstIngredient = burger.ingredients.indexOf(firstIngredient);
        int newIndexSecondIngredient = burger.ingredients.indexOf(secondIngredient);

        Assert.assertEquals(indexFirstIngredient, newIndexSecondIngredient);
        Assert.assertEquals(indexSecondIngredient, newIndexFirstIngredient);
    }

    @Test
    public void getPriceReturnCorrectPrice() {
        Burger burger = new Burger();
        Mockito.when(firstIngredient.getPrice())
                .thenReturn(GenerateRandom.generatingRandomFloatBouned(10f, 100f));
        Mockito.when(secondIngredient.getPrice())
                .thenReturn(GenerateRandom.generatingRandomFloatBouned(10f, 100f));

        burger.setBuns(firstBun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float expectedPrice = (burger.bun.getPrice() * 2) + firstIngredient.getPrice() + secondIngredient.getPrice();

        Assert.assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptReturnCorrectReceipt() {
        Burger burger = new Burger();
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(firstIngredient.getName()).thenReturn(GenerateRandom.generatingRandomFood());
        Mockito.when(firstIngredient.getPrice())
                .thenReturn(GenerateRandom.generatingRandomFloatBouned(10f, 100f));

        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(secondIngredient.getName()).thenReturn(GenerateRandom.generatingRandomFood());
        Mockito.when(secondIngredient.getPrice())
                .thenReturn(GenerateRandom.generatingRandomFloatBouned(10f, 100f));

        burger.setBuns(firstBun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = "(==== " + burger.bun.getName() + " ====)" + "\r\n"
                + "= " + firstIngredient.getType().toString().toLowerCase() + " " + firstIngredient.getName() + " =" + "\r\n"
                + "= " + secondIngredient.getType().toString().toLowerCase() + " " + secondIngredient.getName() + " =" + "\r\n"
                + "(==== " + burger.bun.getName() + " ====)" + "\r\n\r\n"
                + "Price: " + String.format("%f", burger.getPrice()) + "\r\n";

        Assert.assertEquals(expectedReceipt, burger.getReceipt());
    }

}
