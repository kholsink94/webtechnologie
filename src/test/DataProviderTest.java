package test;

import model.DataProvider;
import org.junit.Test;


public class DataProviderTest {
    DataProvider dataProvider = DataProvider.getInstance();

    @Test (expected = AssertionError.class)
    public void testIsHirerEmpty(){
        dataProvider.isHirer("");
    }

    @Test (expected = AssertionError.class)
    public void testIsHirerNull(){
        dataProvider.isHirer(null);
    }

    @Test
    public void testIsHirerGoodInformation(){
        dataProvider.isHirer("Hirer");
    }

    @Test(expected = AssertionError.class)
    public void testAddPersonEmptyName(){
        dataProvider.addPerson("", "Password", "Role");
    }

    @Test(expected = AssertionError.class)
    public void testAddPersonEmptyPassword(){
        dataProvider.addPerson("Username", "", "Role");
    }

    @Test
    public void testAddPersonGoodInformation(){
        dataProvider.addPerson("Username", "Password", "Role");
    }

    @Test (expected = AssertionError.class)
    public void testAddRoomNegativeSM(){
        dataProvider.addRoom(-1, 200, "Ootmarsum", "Owner");
    }

    @Test (expected = AssertionError.class)
    public void testAddNegativeRP(){
        dataProvider.addRoom(20, -1, "Ootmarsum", "Owner");
    }

    @Test (expected = AssertionError.class)
    public void testAddRoomEmptyLocation(){
        dataProvider.addRoom(20, 200, "", "Owner");
    }

    @Test (expected = AssertionError.class)
    public void testAddRoomEmptyOwner(){
        dataProvider.addRoom(20, 200, "Ootmarsum", "");
    }

    @Test
    public void testAddRoomGoodInformation(){
        dataProvider.addRoom(20, 200, "Ootmarsum", "Owner");
    }

    @Test (expected = AssertionError.class)
    public void testGetRoomsEmpty(){
        dataProvider.getSpecificRooms("");
    }

    @Test
    public void testGetRoomsGoodInformation(){
        dataProvider.getSpecificRooms("Owner");
    }

    @Test (expected = AssertionError.class)
    public void testDoesPersonExistsEmpty(){
        dataProvider.doesPersonExist("");
    }

    @Test
    public void testDoesPersonExistsGoodInformation(){
        dataProvider.doesPersonExist("Owner");
    }

    @Test (expected = AssertionError.class)
    public void testGetRoomNegativeSM(){
        dataProvider.getSpecificRooms(-1, 200, "Ootmarsum");
    }

    @Test (expected = AssertionError.class)
    public void testGetRoomNegativeRP(){
        dataProvider.getSpecificRooms(100, -1, "Ootmarsum");
    }

    @Test (expected = AssertionError.class)
    public void testGetRoomEmptyLocation(){
        dataProvider.getSpecificRooms(100, 200, "");
    }

    @Test
    public void testGetRoomGoodInformation(){
        dataProvider.getSpecificRooms(100, 200, "Ootmarsum");
    }

}
