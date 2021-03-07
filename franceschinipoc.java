package sorts.Customs;

import java.security.InvalidKeyException;

import main.ArrayVisualizer;
import sorts.templates.Sort;

final public class franceschinipoc extends Sort {
    public franceschinipoc(ArrayVisualizer arrayVisualizer) {
        super(arrayVisualizer);
        
        this.setSortListName("AAAlog");
        this.setRunAllSortsName("AAAlog");
        this.setRunSortName("AAAlog");
        this.setCategory("custom");
        this.setComparisonBased(true);
        this.setBucketSort(false);
        this.setRadixSort(false);
        this.setUnreasonablySlow(false);
        this.setUnreasonableLimit(0);
        this.setBogoSort(false);
    }
    
    
    public int partition(int[] array, int median, int length, int[] aux){
        
        int lesserpointer = 0;
        int greaterpointer = 0;
        

        for(int i = 0; i <= length; i++){
            if(array[i] > median){
                aux[greaterpointer] = array[i];
                greaterpointer++;
            } else {
                array[lesserpointer] = array[i];
                lesserpointer++;
            }
            Delays.sleep(0.25);
        }
        for(int i = length; i >= lesserpointer; i--){
            array[i] = aux[greaterpointer];
            greaterpointer--;
            Delays.sleep(0.25);
        }
        return lesserpointer;
    }



    public void cks(int[] array, int middle, int length, int[] aux){ //cks = collect keys stably
        int i = 0;
        int k = middle;
        array[i] = array[k];
        i++;
        k++;
        while(k < length && i <= middle){
            if(!linearisElemof(array, 0, i, array[k])){
                Writes.swap(array, i, k, 0.25, true, false);
                i++;
            }
            Delays.sleep(0.5);
            k++;
        }

        

    }

    public boolean linearisElemof(int[] array, int start, int end, int element){
        for(int i = start; i <= end; i++){
            if(array[i] == element){
                System.out.print("t");
                Highlights.markArray(1, i);
                return true;
            }
        }
        System.out.print("f");
        return false;
    }



    public int pivotpartition(int[] array, int median, int length, int[] aux, int start){
        
        int lesserpointer = start;
        int greaterpointer = 0;
        

        for(int i = start; i <= length; i++){
            if(array[i] > median){
                aux[greaterpointer] = array[i];
                greaterpointer++;
            } else {
                array[lesserpointer] = array[i];
                lesserpointer++;
            }
            Delays.sleep(0.75);
        }


        for(int i = 0; i<greaterpointer; i++){
            array[lesserpointer] = aux[i];
            lesserpointer++;
            Delays.sleep(0.75);
        }
        


        return lesserpointer;
    }






    
    @Override
    public void runSort(int[] array, int currentLength, int bucketCount) {
        int[] aux =  Writes.createExternalArray(currentLength);
        int middle = partition(array, (currentLength-1)/6, currentLength, aux);
        
        cks(array, middle, currentLength-1, aux);
        pivotpartition(array, (currentLength-1)/6, currentLength-1, aux, middle);
        Writes.deleteExternalArray(aux);
    }
}
