package utils;
import java.util.Random;
public class randomNumberGenerator {

    Random randNum= new Random();//
    //this is the inbuilt java class in utils package that generates random numbers or strings



    public int randomNameGenerator() {
        return randNum.nextInt(10000);


    }
    public int randomEmailGenerator() {
        return randNum.nextInt(10000);





    }
    public int randomBookIdGenerator() {
        return randNum.nextInt(20);





    }




}
