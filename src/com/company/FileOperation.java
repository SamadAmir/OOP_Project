package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileOperation extends CostOperation{
    File logBookFile;
    File passWord = new File("password.txt");
//    Scanner input = new Scanner(System.in);

    public void updateLogBook(int budget, int expense, String date, String item, int cost) throws IOException {

        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");
        FileWriter writer = new FileWriter(logBookFile,true);
        BufferedWriter br = new BufferedWriter(writer);
        if(item == "nil")
            date = "nil";
        br.append(budget + "\t" + expense + "\t" + date + "\t" + item + "\t" +  cost );
        br.newLine();
        br.close();
    }
    public String getBudgetData(String date) throws IOException{

        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");
        if(!logBookFile.exists()){
            logBookFile.createNewFile();
        }
        FileReader read = new FileReader(logBookFile);
        BufferedReader rd = new BufferedReader(read);
        String budgetData;
        String line, last = null;
        if(rd.readLine() == null){
            budgetData = null;
        }
        else{
            while ((line = rd.readLine()) != null) {
                last = line;
            }
            String[] arr = new String[5];
            int i = 0;
            StringTokenizer st = new StringTokenizer(last, "\t");
            while(st.hasMoreTokens()){
                arr[i] = st.nextToken();
                i++;
            }
            budgetData = arr[0].concat(" " + arr[1]);
        }
        rd.close();
        return budgetData;

    }
    public ArrayList<Log> getLog( String date) throws IOException
    {
        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");
        FileReader read = new FileReader(logBookFile);
        BufferedReader rd = new BufferedReader(read);
        String temp ;
        ArrayList<Log> list1 = new ArrayList<Log>();
        while((temp = rd.readLine()) != null ){
            Log log = new Log();
            StringTokenizer st = new StringTokenizer(temp, "\t");
            String[] str = new String[5];
            int i = 0;
            while(st.hasMoreTokens()){
                str[i] = st.nextToken();
                i++;
            }

            if(str[2].equals(date))
            {
                log.setDate(str[2]);
                log.setItem(str[3]);
                log.setCost(Integer.parseInt(str[4]));
                list1.add(log);
            }
        }
        rd.close();

        return list1;
    }

    public void resetBudget(String date, int budget) throws IOException{

        String last = "";
        String line = "";
        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");

        FileReader read = new FileReader(logBookFile);
        BufferedReader rd = new BufferedReader(read);

        while ((line = rd.readLine()) != null) {
            last = line;
        }
        StringTokenizer st = new StringTokenizer(last, "\t");
        int i = 0;
        String[] arr = new String[5];
        while(st.hasMoreTokens()){
            arr[i] = st.nextToken();
            i++;
        }
        rd.close();

        RandomAccessFile f = new RandomAccessFile("logBook" + getMonthYearName(date) + ".txt", "rw");
        long length = f.length() - 1;
        byte b;
        do {
            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10 && length > 0);
        if (length == 0) {
            f.setLength(length);
        } else {
            f.setLength(length + 1);
        }
        f.close();
        updateLogBook(budget, Integer.parseInt(arr[1]), arr[2], arr[3], Integer.parseInt(arr[4]));

    }

    public ArrayList<Log> getMonthLog(String date) throws IOException {

        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");
        FileReader read = new FileReader(logBookFile);
        BufferedReader rd = new BufferedReader(read);
        String temp;

        ArrayList<Log> list1 = new ArrayList<>();

        while((temp = rd.readLine()) != null ){
            Log log = new Log();
            StringTokenizer st = new StringTokenizer(temp, "\t");
            String[] str = new String[5];
            int i = 0;
            while(st.hasMoreTokens()){
                str[i] = st.nextToken();
                i++;
            }

            log.setDate(str[2]);
            log.setItem(str[3]);
            log.setCost(Integer.parseInt(str[4]));
            list1.add(log);
        }

        rd.close();
        return list1;
    }


    public void deleteLog(String date) {


        logBookFile = new File("logBook" + getMonthYearName(date) + ".txt");
        boolean bool = true;

        if(logBookFile.exists())
            bool  = logBookFile.delete();

        if(bool == true){
            System.out.print("\nFile Deleted Successfully !");
        }
        else
            System.out.print("\nFile for the particular month does not exist !");

    }

    private String getMonthYearName(String date) {

        String name = "";
        if(date.length() == 7)
            date = "00/".concat(date);
        StringTokenizer st = new StringTokenizer(date, "/");
        int i = 0;
        String[] arr = new String[3];
        while(st.hasMoreTokens()){
            arr[i] = st.nextToken();
            i++;
        }
        if(date.length() == 10)
            name = arr[1].concat(arr[2]);
        else if(date.length() == 8)
            name = arr[0].concat(arr[1]);
        return name;
    }

    public String getPassWord() throws IOException {
        if(passWord.length() == 0)
            System.out.print("\nPlease check the password.txt file for errors!");
        FileReader read = new FileReader(this.passWord);
        BufferedReader rd = new BufferedReader(read);
        String pass = rd.readLine().trim();
        rd.close();
        return pass;
    }

    public void setPassWord(String passWord) throws IOException {

        RandomAccessFile f = new RandomAccessFile("password.txt", "rw");
        long length = f.length() - 1;
        byte b;
        do {

            length -= 1;
            f.seek(length);
            b = f.readByte();
        } while(b != 10 && length > 0);
        if (length == 0) {
            f.setLength(length);
        } else {
            f.setLength(length + 1);
        }
        f.close();
        FileWriter writer = new FileWriter(this.passWord,true);
        BufferedWriter br = new BufferedWriter(writer);
        br.write(passWord);
        br.close();
        System.out.print("\nYour password has been changed!");
    }


}