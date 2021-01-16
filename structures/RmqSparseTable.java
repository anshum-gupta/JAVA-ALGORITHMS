package structures;

public class RmqSparseTable {
	 int[][] rangeMinQuery, rangeMaxQuery;
	 final int INF = (int) 1e9;

	    public RmqSparseTable(int[] a) {
	        int n = a.length;
	        rangeMinQuery = new int[32 - Integer.numberOfLeadingZeros(n)][];
	        rangeMinQuery[0] = a.clone();
	        for (int i = 1; i < rangeMinQuery.length; i++) {
	            rangeMinQuery[i] = new int[n - (1 << i) + 1];
	            for (int j = 0; j < rangeMinQuery[i].length; j++) rangeMinQuery[i][j] = Math.min(rangeMinQuery[i - 1][j], rangeMinQuery[i - 1][j + (1 << (i - 1))]);
	        }
	        rangeMaxQuery = new int[32 - Integer.numberOfLeadingZeros(n)][];
	        rangeMaxQuery[0] = a.clone();
	        for (int i = 1; i < rangeMaxQuery.length; i++) {
	            rangeMaxQuery[i] = new int[n - (1 << i) + 1];
	            for (int j = 0; j < rangeMaxQuery[i].length; j++) rangeMaxQuery[i][j] = Math.max(rangeMaxQuery[i - 1][j], rangeMaxQuery[i - 1][j + (1 << (i - 1))]);
	        }
	    }

	    public int min(int i, int j) {
	    	if(i > j) return INF;
	        int k = 31 - Integer.numberOfLeadingZeros(j - i + 1);
	        return Math.min(rangeMinQuery[k][i], rangeMinQuery[k][j - (1 << k) + 1]);
	    }
	    
	    public int max(int i, int j) {
	    	if(i > j) return -INF;
	        int k = 31 - Integer.numberOfLeadingZeros(j - i + 1);
	        return Math.max(rangeMaxQuery[k][i], rangeMaxQuery[k][j - (1 << k) + 1]);
	    }

    public static void main(String[] args) {
        {
            RmqSparseTable st = new RmqSparseTable(new int[] {1, 5, -2, 3});
            System.out.println(1 == st.min(0, 0));
            System.out.println(-2 == st.min(1, 2));
            System.out.println(-2 == st.min(0, 2));
            System.out.println(-2 == st.min(0, 3));
        }
        {
            RmqSparseTable st = new RmqSparseTable(new int[] {1, 5, -2});
            System.out.println(1 == st.min(0, 0));
            System.out.println(-2 == st.min(1, 2));
            System.out.println(-2 == st.min(0, 2));
        }
    }
}
