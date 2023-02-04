class Problem {
    public static void main(String[] args) {
        int x = args.length / 2;
        for (int z = 0; z < x; z++) {
            System.out.printf("%s=%s\n",args[2 * z], args[2 * z + 1]);
        }
    }
}