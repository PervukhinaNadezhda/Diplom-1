package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTests {

    private final Database database = new Database();
    private final String firstBunName =  database.availableBuns().get(0).name;
    private final float firstBunPrice =  database.availableBuns().get(0).price;

    @Test
    public void getNameReturnBunNameTest() {
        Bun bun = new Bun(firstBunName, firstBunPrice);
        Assert.assertEquals(firstBunName, bun.getName());
    }

    @Test
    public void getPriceReturnBunPriceTest() {
        Bun bun = new Bun(firstBunName, firstBunPrice);
        Assert.assertEquals(firstBunPrice,bun.getPrice(), 0f);
    }

}
