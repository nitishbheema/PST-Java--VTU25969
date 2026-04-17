class BuggyCalculator {

    // BUG: skips last element
    public int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++) { // wrong
            sum += arr[i];
        }
        return sum;
    }
}

// FIXED
class FixedCalculator {

    public int sumArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) { // correct
            sum += arr[i];
        }
        return sum;
    }
}
