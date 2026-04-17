class LoopDebug {

    public int sum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // set breakpoint with condition i == 3
        }
        return sum;
    }
}
