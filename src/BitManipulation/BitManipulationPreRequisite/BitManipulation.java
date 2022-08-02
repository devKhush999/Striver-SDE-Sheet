package BitManipulation.BitManipulationPreRequisite;

// https://youtu.be/5iyuU4hQFrw

public class BitManipulation {

    // Time Complexity: O(1)
    public boolean checkBitIsSet_V1(int number, int i){

        // Check if the bit at 'ith' position  (in the 32 Bit representation of integer number)
        // is set to '1' or not
        // It can also be used to get bit at 'ith' position of number
        int bitIsSet = (number >> i) & 1;

        // return ((number >> i) & 1) == 1;
        return bitIsSet == 1;
    }


    // Time Complexity: O(1)
    public boolean checkBitIsSet_V2(int number, int i){

        // Check if the bit at 'ith' position  (in the 32 Bit representation of integer number)
        // is set to '1' or not
        // It can't be used to get bit at 'ith' position of number
        return ((1 << i) & number) != 0;
    }


    // Time Complexity: O(1)
    public int setBitAtIthPosition(int number, int i){

        // Set the bit at 'ith' position (in the 32 Bit representation of integer number) to '1'
        return number | (1 << i);
    }


    // Time Complexity: O(1)
    public int twoToPowerN(int n){
        // Find the 2 to the power n (i.e, 2^n)

        // Easy Solution
        // return (int) Math.pow(2, n);

        // Bit manipulation
        // Why does this works?
        // 2^n means multiply "2" with itself "n" times, so left shift works 😉
        return 1 << n;
    }
}
