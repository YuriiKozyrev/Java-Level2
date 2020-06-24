package Java_Level2.Lesson2_HomeWork.MyExceptions;


public class Main {
    public static void main(String[] args) {
        Array array = new Array();

    do {
        array.setArraySize();

//        try {
            array.checkArraySize(array.testArray);
//        } catch (MyArraySizeException e) {
//            e.printStackTrace();
//        }

    } while (array.testArray.length != array.actualSize);

        array.setArrayElements(array.testArray);
        array.showArrayElements(array.testArray);
        array.checkArrayElements(array.testArray);
    }
}
