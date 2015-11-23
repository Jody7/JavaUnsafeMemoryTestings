import sun.misc.Unsafe;

import java.lang.reflect.Field;

class Impossible {
    private String CANT_TOUCH_ME = "You can not change this private field!!!";

    public void laugh(){
        System.out.println(CANT_TOUCH_ME);
    }

}

public class Rand {


    public static void main(String... args) throws Exception{
        Unsafe unsafe = getUnsafe();

        Impossible impossible = new Impossible();
        impossible.laugh();

        Field field = impossible.getClass().getDeclaredField("CANT_TOUCH_ME");
        String replacedObj = "get changed";

        unsafe.putObject(impossible, unsafe.objectFieldOffset(field), replacedObj);
        //replace the field address with the object itself

        impossible.laugh();

        long randomMemory = unsafe.allocateMemory(30L);
        unsafe.putInt(randomMemory, 13);
        unsafe.putInt(randomMemory + 4L, 1);
        unsafe.putInt(randomMemory + 8L, 115);
        unsafe.putInt(randomMemory + 12L, 1246);
        unsafe.putInt(randomMemory + 16L, 1246);
        unsafe.putInt(randomMemory + 20L, 1246);
        unsafe.putInt(randomMemory + 24L, 1246);
        
        //My very own Array of int's!!!

        for(int i=0; i<100; i = i + 4){
            System.out.println(unsafe.getInt(randomMemory + (long)i) + " at address: " + randomMemory);
        }



        for(int i=0; i<10000; i++){
            //CORRUPT EVERYTHING!
            unsafe.putInt(randomMemory + (long)i, 0);
            System.out.println(randomMemory + (long)i + " corrupted address");
        }


    }


    private static Unsafe getUnsafe() throws Exception{
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return (Unsafe) field.get(null);

    }
    public static int v(int... a)[] {
        //Useless...
        
        return a;
    }


}
