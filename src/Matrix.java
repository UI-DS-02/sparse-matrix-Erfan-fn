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
    }
