public class AddLoop {
/*@(houdini:defaultconstructor) */public AddLoop(){}/* Explicating default constructor here */
    /*@(houdini:parameter:static method) requires x < 1; */
    /*@(houdini:parameter:static method) requires x <= 1; */
    /*@(houdini:parameter:static method) requires x == 1; */
    /*@(houdini:parameter:static method) requires x != 1; */
    /*@(houdini:parameter:static method) requires x >= 1; */
    /*@(houdini:parameter:static method) requires x > 1; */
    /*@(houdini:parameter:static method) requires x < 0; */
    /*@(houdini:parameter:static method) requires x <= 0; */
    /*@(houdini:parameter:static method) requires x == 0; */
    /*@(houdini:parameter:static method) requires x != 0; */
    /*@(houdini:parameter:static method) requires x >= 0; */
    /*@(houdini:parameter:static method) requires x > 0; */
    /*@(houdini:parameter:static method) requires x < -1; */
    /*@(houdini:parameter:static method) requires x <= -1; */
    /*@(houdini:parameter:static method) requires x == -1; */
    /*@(houdini:parameter:static method) requires x != -1; */
    /*@(houdini:parameter:static method) requires x >= -1; */
    /*@(houdini:parameter:static method) requires x > -1; */
    /*@(houdini:parameter:static method) requires y < x; */
    /*@(houdini:parameter:static method) requires y <= x; */
    /*@(houdini:parameter:static method) requires y == x; */
    /*@(houdini:parameter:static method) requires y != x; */
    /*@(houdini:parameter:static method) requires y >= x; */
    /*@(houdini:parameter:static method) requires y > x; */
    /*@(houdini:parameter:static method) requires y < 1; */
    /*@(houdini:parameter:static method) requires y <= 1; */
    /*@(houdini:parameter:static method) requires y == 1; */
    /*@(houdini:parameter:static method) requires y != 1; */
    /*@(houdini:parameter:static method) requires y >= 1; */
    /*@(houdini:parameter:static method) requires y > 1; */
    /*@(houdini:parameter:static method) requires y < 0; */
    /*@(houdini:parameter:static method) requires y <= 0; */
    /*@(houdini:parameter:static method) requires y == 0; */
    /*@(houdini:parameter:static method) requires y != 0; */
    /*@(houdini:parameter:static method) requires y >= 0; */
    /*@(houdini:parameter:static method) requires y > 0; */
    /*@(houdini:parameter:static method) requires y < -1; */
    /*@(houdini:parameter:static method) requires y <= -1; */
    /*@(houdini:parameter:static method) requires y == -1; */
    /*@(houdini:parameter:static method) requires y != -1; */
    /*@(houdini:parameter:static method) requires y >= -1; */
    /*@(houdini:parameter:static method) requires y > -1; */
    /*@(houdini:static method) ensures \result < x; */
    /*@(houdini:static method) ensures \result <= x; */
    /*@(houdini:static method) ensures \result == x; */
    /*@(houdini:static method) ensures \result != x; */
    /*@(houdini:static method) ensures \result >= x; */
    /*@(houdini:static method) ensures \result > x; */
    /*@(houdini:static method) ensures \result < 1; */
    /*@(houdini:static method) ensures \result <= 1; */
    /*@(houdini:static method) ensures \result == 1; */
    /*@(houdini:static method) ensures \result != 1; */
    /*@(houdini:static method) ensures \result >= 1; */
    /*@(houdini:static method) ensures \result > 1; */
    /*@(houdini:static method) ensures \result < 0; */
    /*@(houdini:static method) ensures \result <= 0; */
    /*@(houdini:static method) ensures \result == 0; */
    /*@(houdini:static method) ensures \result != 0; */
    /*@(houdini:static method) ensures \result >= 0; */
    /*@(houdini:static method) ensures \result > 0; */
    /*@(houdini:static method) ensures \result < -1; */
    /*@(houdini:static method) ensures \result <= -1; */
    /*@(houdini:static method) ensures \result == -1; */
    /*@(houdini:static method) ensures \result != -1; */
    /*@(houdini:static method) ensures \result >= -1; */
    /*@(houdini:static method) ensures \result > -1; */
    /*@(houdini:static method) ensures \result < y; */
    /*@(houdini:static method) ensures \result <= y; */
    /*@(houdini:static method) ensures \result == y; */
    /*@(houdini:static method) ensures \result != y; */
    /*@(houdini:static method) ensures \result >= y; */
    /*@(houdini:static method) ensures \result > y; */
    /*@(houdini:static method) requires false; */
    /*@(houdini:static method) ensures x >= 0 ==> \result >= 0; */
    /*@(houdini:static method) ensures y >= 0 ==> \result >= 0; */
    public static int AddLoop(int x, int y) {
        int sum = x;
        if (y > 0) {
            int n = y;
            while (n > 0) {
                sum = sum + 1;
                n = n - 1;
            }
        } else {
            int n = -y;
            while (n > 0) {
                sum = sum - 1;
                n = n - 1;
            }
        }
        return sum;
    }
}

