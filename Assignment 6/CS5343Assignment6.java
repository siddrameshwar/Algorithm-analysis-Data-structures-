/*
 *  Assignment by Siddrameshwar Kadagad
    2021491485
    sxk190071
 */
package cs5343.assignment.pkg6;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Siddrameshwar
 */
public class CS5343Assignment6 {
    private static int table_size;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Assignment by Siddrameshwar Kadagad");
        int InitialTableSize = 53;
        List<String> listOfStrings = initializeList();
        printWords(listOfStrings);
        System.out.println("");
        System.out.println("Executing linear probing");
        String linearProbingHashTable[] = linearHashing(listOfStrings, InitialTableSize);
        
        System.out.println("");
        System.out.println("Executing quadratic probing");
        String quadraticProbingHashTable[] = quadraticHashing(listOfStrings, InitialTableSize);
        
        Scanner s = new Scanner(System.in);
        String input = "dummy";
        System.out.println("");
        System.out.println("Enter a word to check if exists in the hashTable");
        System.out.println("enter 'q' to exit ");
        while(!input.equals("q")) {
            
            input = s.next();
            if(!input.equals("q"))
                System.out.println("Is " + input + " present in the table? " +  dictionaryCheck(input, linearProbingHashTable, table_size ));
            
        }       
        
    }
    
    public static String IsWordPresentInLinearHashTable(String word, String[] hashTable, int tableSize){
        Long wordNum = intValue(word);
        int hashValue = (int) (wordNum % tableSize);
        int i = 0;
        while(hashTable[(hashValue + i)%tableSize] != null && !hashTable[(hashValue + i)%tableSize].equals(word) ){
            i++;
        }
        if(hashTable[(hashValue + i)%tableSize] != null )
            return "yes";
        return "no";
    }
    
    public static void printWords(List<String> list){
        int i = 0;
        System.out.println("");
        System.out.println("Printing input words");
        while(i < list.size()) {
            for(int j = 0; j < 10 && (i < list.size()); j++) {
                System.out.print("" + list.get(i) + "\t");
                i++;
            }
            System.out.println("");
        }
    }
    
    public static Boolean dictionaryCheck(String word, String hashTable[], int tableSize){
        long intValueOfString = intValue(word);
        int hashValue = (int) (intValueOfString % tableSize);
        if(hashTable[hashValue] == null)
            return false;
        else if(hashTable[hashValue].equals(word)){
            return true;
        } else {
            for(int i = (hashValue + 1)%tableSize; i != hashValue; i = (i+1)%tableSize) {
                if(hashTable[i] == null)
                    return false;
                else if(hashTable[i].equals(word)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List<String> initializeList(){
        
        String words = "word world asif abhiskek divya button box cup table laptop mouse tv screen sport karthik dhoni phone home house file folder csk chair srh light bulb fan jacket tub bathroom hosmani tree banana apple tea coffee donkey monkey bus elephant ford fork roof tata birla joshi hair heir word bumper shoe sock chappal sandal door knob lock truck driver uber google oracle microsoft shirt pant tie ac floor ceiling switch bye atheist balcony terrace window typing keyboard lamp glass wire cloth bag red white black brown yellow bottle peanut book box bag cover blanket bedsheet bed hole blackhole star adapter";
        
        String[] list = words.split(" ");
        System.out.println("Number of words is " + list.length);
        List<String> list2 = Arrays.asList(list);
        return list2;
    }
    
    public static String[] quadraticHashing(List<String> listOfStrings, int tableSize){
        System.out.println("tableSize is " + tableSize);
        String hashTable[] = new String[tableSize];
        int numOfElementsInserted = 0;
        int numOfCollision = 0;
        while(numOfElementsInserted <= tableSize/2 && numOfElementsInserted < listOfStrings.size() ) {
            String currString = listOfStrings.get(numOfElementsInserted);
            long intValueOfCurrString = intValue(currString);
            int hashValue =  (int) (intValueOfCurrString % tableSize);
            int i = 0;
            while(hashTable[((hashValue + i*i)%tableSize)] != null){
                i++;
                numOfCollision++;
            }
            hashTable[((hashValue + i*i)%tableSize)] = currString;
            numOfElementsInserted++;
        }       
        
        System.out.println("Number of collisions with table size " + tableSize + " is " + numOfCollision);
        if(numOfElementsInserted < listOfStrings.size()){            
            //printHashTable(hashTable);
            return  quadraticHashing(listOfStrings, nextPrime(2*tableSize));
        }
        //printHashTable(hashTable);
        table_size = tableSize;
        return hashTable;
    }
    
    //Should we send the hashFunction also
    public static String[] linearHashing(List<String> listOfStrings, int tableSize){
        System.out.println("tableSize is " + tableSize);
        String hashTable[] = new String[tableSize];
        int numOfElementsInserted = 0;
        int numOfCollision = 0;
        while(numOfElementsInserted <= tableSize/2 && numOfElementsInserted < listOfStrings.size() ) {
            String currString = listOfStrings.get(numOfElementsInserted);
            long intValueOfCurrString = intValue(currString);
            if(intValueOfCurrString < 0){
                System.out.println("***********************************");
                System.out.println(" currString is " + currString + " and value is " + intValueOfCurrString);
            }
            int hashValue =  (int) (intValueOfCurrString % tableSize);
            if(hashTable[hashValue] == null) {
                hashTable[hashValue] = currString;
                numOfElementsInserted++;
                continue;
            }
            numOfCollision++;
            for(int i = (hashValue + 1)%tableSize; i != hashValue; i = (i+1)%tableSize) {
                if(hashTable[i] == null) {
                    hashTable[i] = currString;
                    break;
                }
                numOfCollision++;
            }
            numOfElementsInserted++;
        }
        System.out.println("Number of collisions with table size " + tableSize + " is " + numOfCollision);
        if(numOfElementsInserted < listOfStrings.size()){            
            //printHashTable(hashTable);
            return  linearHashing(listOfStrings, nextPrime(2*tableSize));
        }
        //printHashTable(hashTable);
        table_size = tableSize;
        return hashTable;
    }
    
    public static void printHashTable(String hashTable[]){
        for(int i = 0; i< hashTable.length; i++){
            System.out.print("i is " + i + " val is " + hashTable[i] + "   ");
        }
        System.out.println("");
    }
    
    public static int nextPrime(int num){
        while(!isPrime(num)){
            num++;
        }
        return num;
    }
    
    
    public static Boolean isPrime(int num){
        for(int i = 2; i <= Math.sqrt(num) + 1; i++){
            if(num % i == 0)
                return false;
        }        
        return true;
    }
    
    public static long intValue(String str){
        long result = 0;
        long multiplier = 1;
        for(int i = str.length() -1 ; i >= 0; i--) {
            int intOfChar = str.charAt(i) - 'a' + 1;
            result += intOfChar * multiplier;
            multiplier *= 26;
        }
        return result-1;
    }
    
    public static String readFileAsString(String fileName)throws Exception { 
      String result = ""; 
      result = new String(Files.readAllBytes(Paths.get(fileName))); 
      return result; 
    }
    
}
