package Java_Level2.Lesson5_HomeWork;

import java.util.Arrays;

public class MultiThreads {
    static final int size = 10000000;

    public static float[] calc (float[] array){
        for (int i = 0; i < array.length ; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return array;
    }

    static public void splitAndCalculate (int quantityThreads){ /* здесь передаю в метод кол-во потоков */
        final int h = size/quantityThreads;
        float[] arraySource = new float[size];
        float[][] arrayDestination = new float[quantityThreads][h];
        Thread[] myThread = new Thread[quantityThreads];

        Arrays.fill(arraySource, 1);

        long a = System.currentTimeMillis();

        //разделяю массив на требуемое количество массивов для потоков
        for (int i = 0; i < quantityThreads; i++) {
            System.arraycopy(arraySource, h*i, arrayDestination[i], 0, h);
        }

        //запускаю потоки в цикле
        for(int i = 0; i < quantityThreads; i++){
            int step = i;
            myThread[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    calc(arrayDestination[step]);
                }
            });
            myThread[i].start();
        }

        try {
            for(int i = 0; i < quantityThreads; i++){
                myThread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //склеиваю массивы в один
        for(int i = 0; i < quantityThreads; i++) {
            System.arraycopy(arrayDestination[i],0, arraySource, h*i, h);
        }
        System.out.println("Общее время работы " + quantityThreads + " потоков, мс: " + (System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            splitAndCalculate(i);
        }
    }
}
