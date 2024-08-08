int[] a = {2,4,3,8};
int[] b = {9,1,5};
//Merge 2 arrays into 1 and sort it
Stream.concat(Arrays.stream(a).boxed(), Arrays.stream(b).boxed()).sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
