public class BubbleSort_GUI {

        public static void bubbleShort_GUI(int[] pole) {
            for (int i = 0; i < pole.length; i++) {
                for (int j = 0; j < pole.length - i -1; j++) {
                    if(pole[j] > pole[j+1]) { // změna znaménka </> řazení vzestupně a sestupně
                        int temp = pole[j];
                        pole[j] = pole[j + 1];
                        pole[j+1] = temp;
                    }
                }
            }
        }
        


    
        public static void main(String[] args) {
            
            int[] pole = {5, 9, 6, 3, 1, 98, 100};

            bubbleShort_GUI(pole);

            for (int i : pole) {
                System.out.print(i+ " ");
            }


        }

    
}
