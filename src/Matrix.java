import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import java.io.FileWriter;
import java.io.IOException;

//--------------
public class Matrix{
    private SinglyLinkedList[]rowList;
    private SinglyLinkedList[]ColList;
    private int [][] compactMatrix;
    private int [][] sparseMatrix;
    private int compactSize;
    private int rowSize;
    private int ColSize;

    public Matrix(int size,int rowSize,int colSize) {


        this.compactSize=size/3;
        this.rowSize=rowSize;
        this.ColSize=colSize;
        this.rowList=new SinglyLinkedList[rowSize];
        this.ColList=new SinglyLinkedList[colSize];
        newMatrixLinkedLists();
    }

    public void insertNode(int row, int column, int element)
    {
        this.rowList[row].add(element,row,column,true);
        this.ColList[column].add(element,row,column,false);
        updateSparseMatrix(row,column,element);
        updateCompactMatrix();

    }

    public SinglyLinkedList[] getRowList() {
        return rowList;
    }

    public void setRowList(SinglyLinkedList[] rowList) {
        this.rowList = rowList;
    }

    public SinglyLinkedList[] getColList() {
        return ColList;
    }

    public void setColList(SinglyLinkedList[] colList) {
        ColList = colList;
    }

    public int[][] getCompactMatrix() {
        return compactMatrix;
    }

    public int[][] getSparseMatrix() {
        return sparseMatrix;
    }

    public void setSparseMatrix(int[][] sparseMatrix) {
        this.sparseMatrix = sparseMatrix;
    }

    public void setCompactMatrix(int[][] compactMatrix) {
        this.compactMatrix = compactMatrix;
    }

    public void updateSparseMatrix(int row,int col,int element)
    {
            this.sparseMatrix[row][col]=element;
    }

        public void updateCompactMatrix()
    {
        int index=0;
        for (int i = 0; i <rowList.length ; i++) {
            SinglyLinkedList.Node current=rowList[i].head;
            for (int j = 0; j < rowList[i].size; j++)
            {
                if (current.element!=0){
                    compactMatrix[index][0]=current.getRow();
                    compactMatrix[index][1]=current.getColumn();
                    compactMatrix[index][2]=current.getElement();
                    index++;
                }
                    current=current.nextInRow;
            }
        }
    }
    private void newMatrixLinkedLists()
    {
        for (int i = 0; i < rowList.length ; i++) {
            this.rowList[i]=new SinglyLinkedList();
        }

        for (int i = 0; i < ColList.length ; i++) {
            this.ColList[i]=new SinglyLinkedList();
        }
        this.sparseMatrix=new int[rowSize][ColSize];
        compactMatrix=new int [compactSize][3];
    }

    public void removeNode(int row, int column)
    {
        this.rowList[row].remove(row, column,true);
        this.ColList[column].remove(row,column,false);
        updateSparseMatrix(row,column,0);
        updateCompactMatrix();
    }

    public boolean searchForValue(int value)//------------------------
    {
        for (int i = 0; i < this.rowList.length; i++) {
            for (int j = 0; j <this.rowList[i].Size() ; j++) {
                if (this.rowList[i].getInRowWithCol(j).getElement()==value){return true;}
            }
        }
        return false;
    }

    public void updateValue(int row, int column, int element,boolean isRow)
    {
        if (isRow)
        {
            this.rowList[row].getInRowWithCol(column).setElement(element);
        }

        else
        {
            this.ColList[column].getInColWithRow(row).setElement(element);
        }
        updateSparseMatrix(row,column,element);
        updateCompactMatrix();
    }

    public  class SinglyLinkedList {

        private Node head;
        private Node tail;
        private int size=0;
        public static class Node {

            private int row;
            private int column;
            private int element;
            private Node nextInRow;
            private Node nextInColumn;

            public Node(int element,int row,int column) {
                this.element = element;
                this.row=row;
                this.column=column;

            }

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public int getColumn() {
                return column;
            }

            public void setColumn(int column) {
                this.column = column;
            }

            public int getElement() {
                return element;
            }

            public void setElement(int element) {
                this.element = element;
            }

            public Node getNextInRow() {
                return nextInRow;
            }

            public void setNextInRow(Node nextInRow) {
                this.nextInRow = nextInRow;
            }

            public Node getNextInColumn() {
                return nextInColumn;
            }

            public void setNextInColumn(Node nextInColumn) {
                this.nextInColumn = nextInColumn;
            }

        }


        public int Size() {
            return size;
        }

        public Node first() {
            return this.head;
        }

        public Node last() {
            return this.tail;
        }

        public void addFirstInRow(int element,int row,int column) {
            Node newNode = new Node(element,row,column);

            if (this.size == 0) {
                head = newNode;
                newNode.nextInRow = null;
                tail = newNode;
            } else {
                newNode.nextInRow = head;
                head = newNode;
            }
            size++;
        }

        public void addFirstInCol(int element,int row,int column) {
            Node newNode = new Node(element,row,column);

            if (this.size == 0) {
                head = newNode;
                newNode.nextInColumn = null;
                tail = newNode;
            } else {
                newNode.nextInColumn = head;
                head = newNode;
            }
            size++;
        }

        public void addLastInRow(int element,int row,int column) {
            Node newNode = new Node(element,row,column);
            if (this.size==0){
                addFirstInRow(element,row,column);
            }
            else
            {
                this.tail.nextInRow = newNode;
                tail = newNode;
                size++;
            }
        }

        public void addLastInCol(int element,int row,int column) {
            Node newNode = new Node(element,row,column);
            if (this.size==0){
                addFirstInCol(element,row,column);
            }
            else
            {
                this.tail.nextInColumn = newNode;
                tail = newNode;
                size++;
            }
        }

        public void removeFirstInRow() {

            if (this.size>1)
            {
                head = this.head.nextInRow;
                size--;
            }
            else
            {
                this.head.nextInRow=null;
                this.head =null;
                size--;
            }
        }

        public void removeFirstInCol() {

            if (this.size>1)
            {
                head = this.head.nextInColumn;
                size--;
            }
            else
            {
                this.head.nextInColumn=null;
                this.head =null;
                size--;
            }
        }

        public void removeLastInRow() {

            tail = this.getPrevInRowWithCol(tail.getColumn());
            tail.nextInRow = null;
            size--;
        }

        public void removeLastInCol() {

            tail = this.getPrevInColWithRow(tail.getRow());
            tail.nextInColumn = null;
            size--;
        }

        public void add(int element,int row,int column,boolean isRow) {

            if (isRow)
            {
                Node current = new Node(element,row,column);
                Node previousRow=this.getPrevInRowWithCol(column);
                Node nextRow=this.getNextInRowWithCol(column);

                if (previousRow==null && nextRow!=null) {
                    addFirstInRow(element,row,column);
                }
                else if (previousRow!=null && nextRow==null)
                {
                    addLastInRow(element,row,column);
                }

                 if(previousRow!=null && nextRow!=null){
                    Node newNode = new Node(element,row,column);
                    previousRow.nextInRow = newNode;
                    newNode.nextInRow = current;
                    size++;
                }

                if(this.size==0){
                    addFirstInRow(element,row,column);
                }
            }
            else
            {
                Node current = new Node(element,row,column);
                Node previousCol=this.getPrevInColWithRow(row);
                Node nextCol=this.getNextInColWithRow(row);

                if (previousCol==null && nextCol!=null) {
                    addFirstInCol(element,row,column);
                }
                else if (previousCol!=null && nextCol==null)
                {
                    addLastInCol(element,row,column);
                }

                if(previousCol!=null && nextCol!=null){
                    Node newNode = new Node(element,row,column);
                    previousCol.nextInColumn = newNode;
                    newNode.nextInColumn = current;
                    size++;
                }

                if(this.size==0){
                    addFirstInCol(element,row,column);
                }
            }

        }
        //------------------------------------------------------------------

        public void remove(int row,int column,boolean isRow) {


            if (isRow) {

                Node current = this.getInRowWithCol(column);
                Node previousRow = this.getPrevInRowWithCol(column);
                Node nextRow = this.getNextInRowWithCol(column);

                if ((previousRow == null && nextRow != null) || size==1) {
                    removeFirstInRow();
                } else if (previousRow != null && nextRow == null) {
                    removeLastInRow();
                }
                if (previousRow != null && nextRow != null) {

                    previousRow.nextInRow = nextRow;
                    current.nextInRow = null;
                    size--;
                }
            }

            else
            {
                Node current = this.getInColWithRow(row);
                Node previousCol = this.getPrevInColWithRow(row);
                Node nextCol = this.getNextInColWithRow(row);

                if ((previousCol == null && nextCol != null) || size==1) {
                    removeFirstInCol();
                } else if (previousCol != null && nextCol == null) {
                    removeLastInCol();
                }
                if (previousCol != null && nextCol != null) {

                    previousCol.nextInColumn = nextCol;
                    current.nextInColumn = null;
                    size--;
                }
            }



        }

        //get next in Col list with the Row
        public Node getNextInColWithRow(int row)
        {
            for (int i = row; i <this.size ; i++) {

                if (this.getInColWithRow(i)!=null && this.getInColWithRow(i).getRow()!=row)
                {
                    return this.getInColWithRow(i);
                }
            }
            return null;
        }

        //get next in Row list with the column
        public Node getNextInRowWithCol(int column)
        {
            for (int i = column; i <this.size ; i++) {

                if (this.getInRowWithCol(i)!=null && this.getInRowWithCol(i).getColumn()!=column)
                {
                       return this.getInRowWithCol(i);
                }
            }
            return null;
        }

        //get previous in Col list with the Row
    public Node getPrevInColWithRow(int row)
    {
        for (int i = row; i >=0 ; i--) {

            if (this.getInColWithRow(i)!=null && this.getInColWithRow(i).getRow()!=row)
            {
                return this.getInColWithRow(i);
            }
        }
        return null;
    }

    //get previous in Row list with the column
    public Node getPrevInRowWithCol(int column)
    {
        for (int i =column; i>=0 ; i--) {

            if (this.getInRowWithCol(i)!=null && this.getInRowWithCol(i).getColumn()!=column)
            {
                return this.getInRowWithCol(i);
            }
        }
        return null;
    }


        //get in the ColList
        public Node getInColWithRow(int row)
        {

            Node current = this.head;
            Node result = null;
            if (current!=null){
                while (current != null) {

                    if (current.getRow()==row) {
                        result = current;
                        break;
                    }
                    current = current.nextInColumn;
                }
            }
            if (this.head!=null)
            {
                if (this.head.getRow()==row){return current;}
            }

            return result;
        }

        //get in the RowList
        public Node getInRowWithCol(int column)
        {

            Node current = this.head;
            Node result = null;
            if (current!=null){
                while (current != null) {

                    if (current.getColumn()==column) {
                        result = current;
                        break;
                    }
                    current = current.nextInRow;
                }
            }
            if (this.head!=null)
            {
                if (this.head.getColumn()==column){return current;}
            }

            return result;
        }

    }

    public String compactToString()
    {
        StringBuilder result = new StringBuilder();
        String[] output;

        for (int i = 0; i <this.compactSize ; i++) {
            for (int j = 0; j <3 ; j++) {
                if (compactMatrix[i][2]!=0)
                {
                    result.append(compactMatrix[i][j]);
                    if (j!=2)
                    {
                        result.append(",");
                    }
                }
            }
            if (i+1<this.compactSize)
            {
                if (compactMatrix[i+1][2]!=0){
                    result.append('\n');
                }
            }
            output=result.toString().split(",");
            // first create file object for file placed at location
            // specified by filepath

            try {
                // create CSVWriter object filewriter object as parameter
                CSVWriter writer = new CSVWriter(new FileWriter("output.csv"), ICSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.NO_QUOTE_CHARACTER,
                        '"',
                        "\"\n");

                // create a List which contains String array

                writer.writeNext(output);

                // closing writer connection
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        }
        return result.toString();
    }
    public String sparseToString()
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i <this.rowSize ; i++) {
            for (int j = 0; j <ColSize ; j++) {
                result.append(sparseMatrix[i][j]);
            }
            result.append('\n');
        }
        return result.toString();
    }


}