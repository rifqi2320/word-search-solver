import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class wordplay {

    public static final int[][] direction = {
        {0, 1},
        {1, 1},
        {1, 0},
        {1, -1},
        {0, -1},
        {-1, -1},
        {-1, 0},
        {-1, 1}
    };

    public static class SearchResult {

        public double time;
        public boolean found;
        public int count;
        public boolean[][] resmat;
        public String word;

        public SearchResult(String word, double time, boolean found, int count, boolean[][] resmat) {
            this.word = word;
            this.time = time;
            this.found = found;
            this.count = count;
            this.resmat = resmat;
        }

        public void show(ArrayList<ArrayList<String>> mat) {
            System.out.println();
            System.out.println("Word: " + this.word);
            System.out.println();
            if (found){
              for (int i = 0; i < mat.size(); i++) {
                for (int j = 0; j < mat.get(i).size(); j++) {
                    if (this.resmat[i][j]) {
                        System.out.print(mat.get(i).get(j));
                    } else {
                        System.out.print("-");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            } else {
              System.out.println("Word not found");
              System.out.println();
            }
            
            System.out.println(String.format("Time taken: %.3f ms", time));
            System.out.println(String.format("Comparison count: %d", count));
            System.out.println();
        }
    }

    public static class Word {

        public String word;

        public Word(String word) {
            this.word = word;
        }

        public SearchResult search(ArrayList<ArrayList<String>> mat) {
            long startTime = System.nanoTime();
            int count = 0;
            int counter = 0;
            boolean found = false;
            boolean[][] resmat = new boolean[mat.size()][mat.get(0).size()];
            matrixloop:
            for (int n = 0; n < mat.size(); n++) {
                for (int m = 0; m < mat.get(n).size(); m++) {
                    for (int[] dir : direction) {
                        counter = 0;
                        while (true) {
                            try {
                                count += 1;
                                if (mat.get(n + dir[1] * counter).get(m + dir[0] * counter).equals(Character.toString(word.charAt(counter)))) {
                                    counter += 1;
                                } else {
                                    break;
                                }
                                if (counter == this.word.length()) {
                                  found = true;
                                  for (int i = 0; i < this.word.length(); i++){
                                    resmat[n + dir[1] * i][m+dir[0] * i] = true;
                                  }
                                  break matrixloop;
                              }
                            } catch (Exception e) {
                                count -= 1;
                                break;
                            }
                        }
                      
                    }
                }
            }
            return new SearchResult(
                    this.word,
                    ((double) (System.nanoTime() - startTime)/1000000),
                    found,
                    count,
                    resmat
            );
        }

        @Override
        public String toString() {
            return this.word;
        }
    }

    public static ArrayList<ArrayList<String>> mat;
    public static ArrayList<Word> words;


    public static void main(String args[]) {
        // Cek jumlah argumen
        System.out.println(wordplay.class.getName());
        int count = args.length;
        if (count == 0) {
            System.out.println("Missing argument : Text Filename");
            return;
        } else if (count > 1) {
            System.out.println("Too many arguments");
            return;
        }

        // Pembacaan File
        boolean wordFlag = false;
        words = new ArrayList<Word>();
        mat = new ArrayList<ArrayList<String>>();
        mat.add(new ArrayList<String>());
        int y = 0;

        File f = new File(args[0]);
        try {
            Scanner in = new Scanner(f);
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split(" ");
                for (String word : line) {
                    if (word.equals("")) {
                        wordFlag = true;
                    } else {
                        if (wordFlag) {
                            words.add(new Word(word));
                        } else {
                            mat.get(y).add(word);
                        }
                    }

                }
                if (!wordFlag) {
                    mat.add(new ArrayList<String>());
                    y += 1;
                }

            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }


        // Main Program
        for (int i = 0; i < words.size(); i++) {
            SearchResult res = words.get(i).search(mat);
            res.show(mat);
        }
    }
}
