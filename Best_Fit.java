package Operating_System_Programs;

import java.util.ArrayList;
public class Best_Fit {
    private static final int NO_ALLOCATION = -255; 
    private static int findMaxElement(int[] array) {
        int max = -1;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    private static int findBestFit(int[] blockSizes, int processSize) {
        int minDiff = findMaxElement(blockSizes);
        int index = NO_ALLOCATION; 
        for(int i=0 ; i < blockSizes.length ; i++) { 
            if(blockSizes[i] - processSize < minDiff && blockSizes[i] - processSize >= 0) {
                minDiff = blockSizes[i] - processSize;
                index = i;
            }
        }
        return index;
    }
    static ArrayList<Integer> bestFit(int[] sizeOfBlocks, int[] sizeOfProcesses) {
        ArrayList<Integer> memAlloc = new ArrayList<>();
        for(int processSize : sizeOfProcesses) {
            int chosenBlockIdx = findBestFit(sizeOfBlocks, processSize); 
            memAlloc.add(chosenBlockIdx); 
            if(chosenBlockIdx != NO_ALLOCATION) { 
                sizeOfBlocks[chosenBlockIdx] -= processSize; 
            }
        }
        return memAlloc;
    }
    public static void printMemoryAllocation(ArrayList<Integer> memAllocation) {
        System.out.println("Process No.\tBlock No.");
        System.out.println("===========\t=========");
        for (int i = 0; i < memAllocation.size(); i++) {
            System.out.print(" " + i + "\t\t");
            if (memAllocation.get(i) != NO_ALLOCATION)
                System.out.print(memAllocation.get(i));
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}