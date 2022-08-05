package Strings.CompareVersionNumbers;

public class CompareVersionNumbers {
    /****************************** Efficient Solution 1 : Two Pointers **********************************
     * Time Complexity: O(max(v1, v2))
        * "v1" & "v2" are the lengths of two input strings 'version1' & 'version2'
     * Space Complexity: O(1)
     */
    public int compareVersion_V1(String version1, String version2) {
        int v1 = version1.length();
        int v2 = version2.length();
        int i = 0, j = 0;

        for (; i < v1 && j < v2; i++, j++){
            int v1_revision = 0, v2_revision = 0;

            while (i < v1 && version1.charAt(i) != '.') {
                int digit = version1.charAt(i++) - '0';
                v1_revision = 10 * v1_revision + digit;
            }
            while (j < v2 && version2.charAt(j) != '.') {
                int digit = version2.charAt(j++) - '0';
                v2_revision = 10 * v2_revision + digit;
            }
            if (v1_revision > v2_revision)
                return 1;
            if (v2_revision > v1_revision)
                return -1;
        }

        while (i < v1){
            int v1_revision = 0;
            while (i < v1 && version1.charAt(i) != '.') {
                int digit = version1.charAt(i++) - '0';
                v1_revision = 10 * v1_revision + digit;
            }
            if (v1_revision > 0)
                return 1;
            i++;
        }

        while (j < v2){
            int v2_revision = 0;
            while (j < v2 && version2.charAt(j) != '.') {
                int digit = version2.charAt(j++) - '0';
                v2_revision = 10 * v2_revision + digit;
            }
            if (v2_revision > 0)
                return -1;
            j++;
        }
        return 0;
    }


    /****************************** Efficient Solution 2 : Two Pointers **********************************
     * Time Complexity: O(max(v1, v2))
        * "v1" & "v2" are the lengths of two input strings 'version1' & 'version2'
     * Space Complexity: O(1)
     */
    public int compareVersion_V2(String version1, String version2) {
        int v1 = version1.length();
        int v2 = version2.length();
        int i = 0, j = 0;

        for (; i < v1 || j < v2; i++, j++){
            int v1_revision = 0, v2_revision = 0;

            while (i < v1 && version1.charAt(i) != '.') {
                int digit = version1.charAt(i++) - '0';
                v1_revision = 10 * v1_revision + digit;
            }
            while (j < v2 && version2.charAt(j) != '.') {
                int digit = version2.charAt(j++) - '0';
                v2_revision = 10 * v2_revision + digit;
            }

            if (v1_revision > v2_revision)
                return 1;
            if (v2_revision > v1_revision)
                return -1;
        }
        return 0;
    }
}
