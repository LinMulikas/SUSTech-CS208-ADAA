import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Lab1_Ver_1{
    static class Person{
        static HashMap<String, Person> boys = new HashMap<>();
        static HashMap<String, Person> girls = new HashMap<>();

        String name;
        String pairName = null;
        ArrayList<String> preferList = new ArrayList<>();

        Person(String str){
            this.name = str;
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine().split(" ")[0]);
        String[] input = new String[2*N + 2];
        for(int i = 0; i < 2*N + 2; i++){
            input[i] = in.readLine();
        }

        int index = 0;
        String[] line = input[index].split(" ");
        String[] boysName = line.clone();
        for(int i = 0; i < N; i++){
            Person.boys.put(line[i], new Person(line[i]));
        }
        index++;

        line = input[index].split(" ");
        String[] girlName = line.clone();
        for(int i = 0; i < N; i++){
            Person.girls.put(line[i], new Person(line[i]));
        }
        index++;

        // Boys' name initialization
        line = input[index].split(" ");        
        
        for(int i = 0; i < N; i++){
            line = input[index].split(" ");
            index++;
            Person iBoy = Person.boys.get(boysName[i]);
            for(String str : line){
                iBoy.preferList.add(str);
            }
        }
 
        for(int i = 0; i < N; i++){
            line = input[index].split(" ");
            index++;
            Person iGirl = Person.girls.get(girlName[i]);
            for(String str : line){
                iGirl.preferList.add(str);
            }
        }

        ArrayList<String> singleBoys = new ArrayList<>();
        singleBoys.addAll(Person.boys.keySet());

        while(!singleBoys.isEmpty()){
            String strBoyName = singleBoys.remove(0);
            Person iBoy = Person.boys.get(strBoyName);

            while(!iBoy.preferList.isEmpty()){
                String strGirlName = iBoy.preferList.remove(0);
                Person iGirl = Person.girls.get(strGirlName);

                if(iGirl.pairName == null){
                    iGirl.pairName = strBoyName;
                    iBoy.pairName = strGirlName;
                    break;
                }else{
                    if(iGirl.preferList.indexOf(strBoyName) < iGirl.preferList.indexOf(iGirl.pairName)){
                        Person thatBoy = Person.boys.get(iGirl.pairName);
                        thatBoy.pairName = null;
                        singleBoys.add(thatBoy.name);

                        iGirl.pairName = strBoyName;
                        iBoy.pairName = strGirlName;
                        break;
                    }
                }
            }
        }
    
        for(String str : boysName){
            System.out.printf("%s %s\n", Person.boys.get(str).name,Person.boys.get(str).pairName);
        }
    }
}

