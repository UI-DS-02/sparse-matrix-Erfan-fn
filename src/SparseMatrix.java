import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;

import java.io.*;

public class SparseMatrix {
    public static void main(String[] args) {

        Matrix matrix = null;
        String line = "";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("M(10,5).csv"));
            int rowSize=0;
            int colSize=0;
            int totSize=0;
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] sparseMatrix = line.split(",");    // use comma as separator
                for (int i = 0; i < sparseMatrix.length; i++)
                {
                    if (rowSize==0){colSize++;}
                    totSize++;
                }
                rowSize++;
            }
        br.close();

            matrix=new Matrix(totSize,rowSize,colSize);
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br1 = new BufferedReader(new FileReader("M(10,5).csv"));
            int row=0;
            while ((line = br1.readLine()) != null)   //returns a Boolean value
            {
                String[] sparseMatrix = line.split(",");    // use comma as separator
                for (int i = 0; i < sparseMatrix.length; i++)
                {
                    if (Integer.parseInt(sparseMatrix[i])!=0)
                    {
                        matrix.insertNode(row,i,Integer.parseInt(sparseMatrix[i]));
                    }
                    matrix.getSparseMatrix()[row][i]=Integer.parseInt(sparseMatrix[i]);
                }
                row++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("an error acquired");
        }
        //test the functions
        matrix.updateValue(1,2,1000,true);
        matrix.updateValue(1,2,1000,false);

        matrix.removeNode(2,0);

        matrix.insertNode(0,0,34);

        String result;
        result=matrix.compactToString();

        String resultCompact=matrix.compactToString();

        //write the output into the csv file


        String[]output=result.toString().split(",");
        System.out.println(result);
        // first create file object for file placed at location
        // specified by filepath
        String[] outputCompact = resultCompact.toString().split("\n");

        try {
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(new FileWriter("output.csv"), ICSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    '"',
                    "\"\n");

            // create a List which contains String array
            CSVWriter writer = new CSVWriter(new FileWriter("outputCompact.csv"), ',',
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext(output);
            for (String line1 : outputCompact) {
                String[] data = line1.split(",");
                writer.writeNext(data);
            }

            // closing writer connection
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IOException e) {

        String resultSparse=matrix.sparseToString();

        String[] outputSparse = resultSparse.toString().split("\n");

        try {
            CSVWriter writer = new CSVWriter(new FileWriter("outputSparse.csv"), ',',
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            for (String line1 : outputSparse) {
                String[] data = line1.split(",");
                writer.writeNext(data);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(matrix.searchForValue(999));

    }

//    public String commandPerformer(Matrix matrix, String command) {
//        String result = "";
//        String[] array = command.split(" ");
//        if (array[0].equals("insertNode")) {
//            matrix.insertNode(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
//            result="Node inserted";
//        }
//        if (array[0].equals("removeNode")) {
//            matrix.removeNode(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
//            result="removeNode";
//        }
//        if (array[0].equals("searchForValue")) {
//            matrix.searchForValue(Integer.parseInt(array[0]));
//        }
//        if (array[0].equals("addFirst")) {
//            linkedList.addFirst(Integer.parseInt(array[1]));
//        }
//        if (array[0].equals("addLast")) {
//            linkedList.addLast(Integer.parseInt(array[1]));
//        }
//        if (array[0].equals("remove")) {
//            result = String.valueOf(linkedList.remove(Integer.parseInt(array[1])));
//        }
//        if (array[0].equals("get")) {
//            try {
//                result = String.valueOf(linkedList.get(Integer.parseInt(array[1])));
//            } catch (NullPointerException e) {
//                result="";
//            }
//        }
//        if (array[0].equals("add")) {
//            linkedList.add(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
//        }
//        if (array[0].equals("sort")) {
//            linkedList.sort(linkedList);
//        }
//        if (array[0].equals("removeFirst")) {
//            result = String.valueOf(linkedList.removeFirst());
//        }
//        if (array[0].equals("removeLast")) {
//            result = String.valueOf(linkedList.removeLast());
//        }
//        if (array[0].equals("last")) {
//            result = String.valueOf(linkedList.last());
//        }
//        if (array[0].equals("first")) {
//            result = String.valueOf(linkedList.first());
//        }
//
//
//        return result;
//    }
}


